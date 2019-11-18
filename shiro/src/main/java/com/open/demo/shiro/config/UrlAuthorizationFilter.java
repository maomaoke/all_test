package com.open.demo.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-14-10:40 下午
 */
@Slf4j
public class UrlAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String url = WebUtils.toHttp(request).getRequestURI();
        return getSubject(request, response).isPermitted(url);
    }

}
