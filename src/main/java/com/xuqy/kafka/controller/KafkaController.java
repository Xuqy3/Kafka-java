package com.xuqy.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuqy.kafka.entity.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;


@Slf4j
@RequestMapping("/kafka")
@RestController
public class KafkaController {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @GetMapping("/send/{msg}")
    public void KafkaProducer(@PathVariable String msg, HttpServletResponse response) throws JsonProcessingException {
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", "192.168.7.142:6667,192.168.7.143:6667,192.168.7.144:6667");
        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
        //创建生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

//        将消息转为json对象
        Log log = new Log();
        log.setStatus(String.valueOf(response.getStatus()));
        log.setMsg(msg);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.setCreateTime(df.format(new Date()));

        String msgToJson = MAPPER.writeValueAsString(log);
        System.out.println(msgToJson);

        ProducerRecord record = new ProducerRecord<String, String>("kl", "msg", msgToJson);
        //发送记录
        producer.send(record);
        producer.close();
    }

    @GetMapping("/receive")
    public Log KafkaConsumer() throws JsonProcessingException {
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", "192.168.7.142:6667,192.168.7.143:6667,192.168.7.144:6667");
        //必须指定消费者组
        props.put("group.id", "test");
        props.put("session.timeout.ms", "15000");
        //设置数据key和value的序列化处理类
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
        //创建消息者实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //订阅kafkaLog的消息
        consumer.subscribe(Arrays.asList("kl"));
        //到服务器中读取记录
//        List<Log> logs = new ArrayList<>();
        //持续消费,如果读取到消息，添加到集合中就结束
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//            if (null != records && records.count() > 0) {
//                for (ConsumerRecord<String, String> record : records) {
//                    //将|JSon转换为实体类并返回
//                    Log log = MAPPER.readValue(record.value(), Log.class);
//                    System.out.println(record.value());
//                    System.out.println(log.toString());
//                    logs.add(log);
//                }
//                break;
//            }
//        }
//        return logs.toString();

//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//            for (ConsumerRecord<String, String> record : records) {
//                //将|JSon转换为实体类并返回
//                Log log = MAPPER.readValue(record.value(), Log.class);
//                System.out.println(record);
//                System.out.println(log.toString());
//                logs.add(log);
//            }
//        }
//        int i = 1;
        while (true) {
//            System.out.println(i++);
            ConsumerRecords<String, String> records = consumer.poll(5000);
            if (null != records && records.count() > 0) {
                for (ConsumerRecord<String, String> record : records) {
                    //将|JSon转换为实体类并返回
                    Log log = MAPPER.readValue(record.value(), Log.class);
                    System.out.println("record.value"+record.value());
                    System.out.println("log"+log.toString());
//                    logs.add(log);
                    consumer.close();
                    return log;

                }
            }
        }



    }
}





