package com.open.demo.rabbit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenkechao
 * @date 2019-07-02 21:33
 */
public class HelloQueueTest extends RabbitApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    public void test() {
        sender.send();
    }
}
