package com.open.demo.jdbc.service.impl;

import com.open.demo.jdbc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenkechao
 * @date 2019-07-04 23:22
 */
@Service

public class TestServiceImpl implements TestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void selectDept() {
        System.out.println("查询开始");
        String selectSql = "select * from t_dept where id =1 lock in share mode ";
        jdbcTemplate.execute(selectSql);
        jdbcTemplate.execute(selectSql);

    }

    @Transactional(rollbackFor = {Exception.class}, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void updateDept() {
        System.out.println("更新开始");
        String updateSql = "update t_dept set name = '2222' where id = 1";
        jdbcTemplate.execute(updateSql);
    }
}
