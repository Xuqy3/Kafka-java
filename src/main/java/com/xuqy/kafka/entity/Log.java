package com.xuqy.kafka.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Log implements Serializable {
    private BigDecimal id;

    private String createTime;

    private String status;

    private String msg;

    @Override
    public String toString() {
        return "{" +
                "\"id\"=\"" + id + '\"' +
                ",\"createTime\"=\"" + createTime + '\"' +
                ", \"status\"=\"" + status + '\"' +
                ", \"msg\"=\"" + msg + '\"' +
                '}';
    }
}
