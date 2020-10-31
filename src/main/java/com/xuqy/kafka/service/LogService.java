package com.xuqy.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuqy.kafka.entity.Log;
import com.xuqy.kafka.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    public List<Log> findAll() {
        return logMapper.selectAll();
    }

    public void insert(Log log) {
        log.toString();
        logMapper.insert(log);
    }
}
