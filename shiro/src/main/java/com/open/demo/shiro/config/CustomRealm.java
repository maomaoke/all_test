package com.open.demo.shiro.config;

import com.google.common.collect.Sets;
import com.open.demo.shiro.pojo.entity.Users;
import com.open.demo.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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

        //获取用户所有权限
        String username = (String) principals.getPrimaryPrincipal();
        List<String> permissions = userService.listPermissionByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(Sets.newConcurrentHashSet(permissions));
        return authorizationInfo;
    }

}
