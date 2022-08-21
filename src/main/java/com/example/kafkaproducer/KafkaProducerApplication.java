package com.example.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerApplication {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerApplication.class);

    public static void main(String[] args) {
        System.out.println();
        logger.info("***************************************************");
        logger.info("******** Kafka Producer BOOT Sunning Start ********");
        logger.info("***************************************************");
        SpringApplication.run(KafkaProducerApplication.class, args);
        System.out.println();
        logger.info("***************************************************");
        logger.info("***** Kafka Producer BOOT Start Successfully ******");
        logger.info("***************************************************");
    }
}
