package com.example.rabbitMQWithSpringBootExampleProducerProject.controller;

import com.example.rabbitMQWithSpringBootExampleProducerProject.model.MessageA;
import com.example.rabbitMQWithSpringBootExampleProducerProject.service.MessageAProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class RabbitMQWebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQWebController.class);

    @Autowired
    private MessageAProducerService messageAProducerService;

    @GetMapping(value = "/messageAProducer")
    public ResponseEntity<MessageA> messageAProducer(@RequestParam("messageId") String messageId,
                                                     @RequestParam("messageType") String messageType,
                                                     @RequestParam("message") String message) {

        MessageA messageA = MessageA.builder()
                .id(messageId)
                .type(messageType)
                .message(message)
                .build();


        LOGGER.info("messageAProducer: new notification: {} arrived", messageA);

        messageAProducerService.sendToQueueA(messageA);

        return ResponseEntity.ok(messageA);
    }

}



