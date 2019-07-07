package com.open.demo.jdbc;

import com.open.demo.jdbc.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void contextLoads() throws InterruptedException {

        Thread thread1 = new Thread(()-> {
            testService.selectDept();
        });
        thread1.setName("thread1");

        Thread.sleep(100);
        Thread thread2 = new Thread(()-> {
            testService.updateDept();
        });

        thread2.setName("thread2");

        thread1.start();
        thread2.start();

        Thread.sleep(1000000000);
    }

}
