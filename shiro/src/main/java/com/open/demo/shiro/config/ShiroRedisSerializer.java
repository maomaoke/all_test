package com.open.demo.shiro.config;

import org.apache.shiro.session.Session;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Objects;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-14-6:16 下午
 */
public class ShiroRedisSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    private static final byte[] EMPTY_ARRAY = new byte[0];

    @Override
    public byte[] serialize(Object session) throws SerializationException {
        if (Objects.isNull(session)) {
            return EMPTY_ARRAY;
        }
        try {
            return serializer.convert(session);
        } catch (Exception e) {
            return EMPTY_ARRAY;
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
       if (bytes == null || bytes.length == 0) {
           return null;
       }

       try {
           return deserializer.convert(bytes);
       } catch (Exception e) {
           throw  new ShiroRedisDeserializeException("序列化失败");
       }
    }
}
