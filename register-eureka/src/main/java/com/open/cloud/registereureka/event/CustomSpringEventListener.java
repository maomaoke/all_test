package com.open.cloud.registereureka.event;

import org.springframework.context.ApplicationListener;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-30-4:34 下午
 */
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
    }


}
