package com.xuqy.kafka.mapper;

import com.xuqy.kafka.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Component
public interface LogMapper {

    int deleteByPrimaryKey(BigDecimal id);

    int insert(Log record);

    Log selectByPrimaryKey(BigDecimal id);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);
}
