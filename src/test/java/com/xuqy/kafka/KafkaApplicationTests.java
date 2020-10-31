package com.xuqy.kafka;

import com.xuqy.kafka.controller.KafkaController;
import com.xuqy.kafka.controller.LogController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaApplicationTests {

    @Autowired
    LogController logController;

    @Autowired
    KafkaController kafkaController;

    @Test
    void contextLoads() {
//        System.out.println(logController.findAll());
//        logController.insert("ndjcnd","400");
//        kafkaController.KafkaProducer();
//        kafkaController.KafkaConsumer();
    }

}
