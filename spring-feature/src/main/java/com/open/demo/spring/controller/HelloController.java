package com.open.demo.spring.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.open.demo.spring.dto.HelloDTO;
import com.open.demo.spring.utils.DateTimeUtils;
import com.open.demo.spring.utils.JsonUtils;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-12-19-7:36 下午
 */
@Slf4j
@RestController
public class HelloController {

    @PostMapping("/hello")
    public String hello(@RequestBody HelloDTO param) {
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        Date startTime = param.getStartTime();
        String dateStr = DateTimeUtils.dateToStr(startTime);
        log.info("{}", dateStr);
        hashMap.put("date", dateStr);
        return JsonUtils.obj2Json(hashMap);
    }

    @PostMapping("/hello1")
    public String hello1(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        String dateStr = DateTimeUtils.dateToStr(date);
        log.info("{}", dateStr);
        hashMap.put("date", dateStr);
        return JsonUtils.obj2Json(hashMap);
    }

    @PostMapping("/hello2")
    public String hello2(@ApiParam(example = "2019-12-12 00:00:00") @RequestParam String str) {
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        Date date = DateTimeUtils.strToDate(str);
        String dateStr = DateTimeUtils.dateToStr(date);
        log.info("{}", dateStr);
        hashMap.put("date", dateStr);
        return JsonUtils.obj2Json(hashMap);
    }

    @PostMapping("/hello_list")
    public String helloList(@RequestBody List<HelloDTO> param) {
        return param.toString();
    }
}
