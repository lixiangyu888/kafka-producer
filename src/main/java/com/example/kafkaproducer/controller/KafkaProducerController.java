package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.service.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: kafka生产者
 * @author: Li Xiangyu
 * @date: 2022/8/12 20:51
 **/
@Api(tags = "kafka生产者")
@EnableSwagger2
@RestController
@RequestMapping("/producer")
public class KafkaProducerController {

    public static final Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);
    @Resource
    private ProducerService producerService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.topic-name}")
    private String topicName;

    @GetMapping("/send")
    @ApiOperation(value = "发消息", notes = "向kafka发消息")
    public void send(String topic,String message){
        // send ( topic , 消息信息 )
        kafkaTemplate.send(topic,message);
        logger.info(topic+" : "+message);
    }

    @GetMapping("/test")
    @ApiOperation(value = "发消息", notes = "向kafka发消息")
    public void test(){
        producerService.sendMessage(topicName, "测试");
        logger.info(topicName+" : 测试");
    }

    @GetMapping("/testByBytes")
    @ApiOperation(value = "发消息", notes = "向kafka发消息")
    public void sendMessageByBytes(String key,String message){
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, 0, System.currentTimeMillis(), key, message);
        producerRecord.headers().add("user", message.getBytes());
        producerService.sendMessage(producerRecord);
        logger.info("message : "+message);
    }

    @GetMapping("/testByHeaders")
    @ApiOperation(value = "发消息", notes = "向kafka发消息")
    public void sendMessage(String event){
        Map<String, Object> map = new HashMap<>();
        map.put("user", "li.xy");
        MessageHeaders headers = new MessageHeaders(map);
        Message<String> message = MessageBuilder.createMessage(event, headers);
        kafkaTemplate.setDefaultTopic(topicName);
        producerService.sendMessage(message);
        logger.info("message : "+message);
    }
}
