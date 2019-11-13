package com.open.demo.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.demo.shiro.pojo.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen-ke-chao
 * @since 2019-11-13
 */
@Repository
public interface UsersMapper extends BaseMapper<Users> {

    Users getUser(@Param("username") String username, @Param("password") String password);

}
