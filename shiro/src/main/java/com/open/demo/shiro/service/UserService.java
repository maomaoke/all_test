package com.open.demo.shiro.service;

import com.open.demo.shiro.pojo.entity.Users;

import java.util.List;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-4:40 下午
 */
public interface UserService {

    Users getUser(String username, String password);

    List<String> listPermissionByUsername(String username);
}
