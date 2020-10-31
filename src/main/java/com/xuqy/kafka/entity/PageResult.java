package com.xuqy.kafka.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageResult {

    private int pageNum;   //当前页数

    private int totalPageNum;  // 总页数

    private long recordsSize;   // 总记录数

    private List<?> rows;   // 每行显示的内容
}
