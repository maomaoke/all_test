package com.open.demo.shiro.service;

import com.open.demo.shiro.pojo.entity.Users;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-4:40 下午
 */
public interface UserService {

    Users getUser(String username, String password);
}
