package com.xuqy.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuqy.kafka.entity.Log;
import com.xuqy.kafka.entity.PageResult;
import com.xuqy.kafka.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/kafka")
public class LogController {

    @Autowired
    private LogService logService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @GetMapping("/findAll")
    public PageResult findAll(
            @RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo){

        PageHelper.startPage(pageNo,10);
        List<Log> logs = logService.findAll();

        PageInfo<Log> logPageInfo = new PageInfo<>(logs);
        PageResult logsPage = new PageResult();
        logsPage.setRecordsSize(logPageInfo.getTotal());
        logsPage.setPageNum(logPageInfo.getPageNum());
        logsPage.setRows(logPageInfo.getList());
        logsPage.setTotalPageNum(logPageInfo.getPages());
        return logsPage;
    }

    @PostMapping("/insert")
    public void insert(@RequestBody String newObject) throws JsonProcessingException {
//        System.out.println(msg);
        Log log = MAPPER.readValue(newObject, Log.class);
        System.out.println("insert:"+log.toString());
        logService.insert(log);
    }
}
