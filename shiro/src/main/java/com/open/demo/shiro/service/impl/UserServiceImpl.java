package com.open.demo.shiro.service.impl;

import com.open.demo.shiro.dao.UsersMapper;
import com.open.demo.shiro.pojo.entity.Users;
import com.open.demo.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-4:40 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users getUser(String username, String password) {
        return usersMapper.getUser(username, password);
    }
}
