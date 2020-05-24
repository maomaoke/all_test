package com.open.demo.activiti.test;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkechao
 * @date 2020/5/24 8:13 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Before
    public void setUp() {
    }

    @Test
    public void startProcess() {
        Map<String, String> properties = new HashMap<>();
        properties.put("dueDate", "2013-03-11");
        String listing = "1. 16寸MacBook Pro一台\n";
        listing += "2. 27寸显示器一台";
        properties.put("listing", listing);
        properties.put("amountMoney", "22000");

        identityService.setAuthenticatedUserId("chenkechao");
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("purchase").singleResult();
        formService.submitStartFormData(processDefinition.getId(), properties);
    }

    @Test
    public void leaderApproved() {
        Task task = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
        taskService.claim(task.getId(), "kermit");
        Map<String, String> properties = new HashMap<>();
        properties.put("deptLeaderApproved", "true");
        formService.submitTaskFormData(task.getId(), properties);
    }

    @Test
    public void contactSupportCrew() {
        Task task = taskService.createTaskQuery().taskCandidateGroup("supportCrew").singleResult();
        taskService.claim(task.getId(), "kermit");
        Map<String, String> properties = new HashMap<>();
        properties.put("supplier", "苹果公司");
        properties.put("bankName", "中国工商银行");
        properties.put("bankAccount", "203840240274247293");
        properties.put("planDate", "2020-03-20");
        formService.submitTaskFormData(task.getId(), properties);
    }

    /**
     * 子流程--财务审批
     */
    @Test
    public void subProcessTreasurer() {
        Task task = taskService.createTaskQuery().taskCandidateGroup("treasurer").singleResult();
        taskService.claim(task.getId(), "kermit");
        Map<String, String> properties = new HashMap<>();
        properties.put("treasurerApproved", "true");
        formService.submitTaskFormData(task.getId(), properties);
    }


}
