package com.open.demo.spring.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-30-4:17 下午
 */
@Getter
public class CustomSpringEvent extends ApplicationEvent {

    private String message;

    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }


}
