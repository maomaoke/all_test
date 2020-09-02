package com.open.demo.rabbit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.demo.rabbit.entity.User;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chen-ke-chao
 * @since 2020-08-28
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
