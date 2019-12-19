package com.open.demo.shiro;

import org.apache.shiro.session.SessionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-11-19-8:41 下午
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({SessionException.class})
    public void sessionExceptionHandler(SessionException e) {
        System.out.println(e.toString());
    }
}
