package com.open.demo.shiro.config;

import com.open.demo.shiro.pojo.entity.Users;
import com.open.demo.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-13-4:09 下午
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String principal = usernamePasswordToken.getUsername();
        String credentials = new String(usernamePasswordToken.getPassword());

        Users user = userService.getUser(principal, credentials);

        if (Objects.nonNull(user)) {
            return new SimpleAuthenticationInfo(principal, credentials, getName());
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

}
