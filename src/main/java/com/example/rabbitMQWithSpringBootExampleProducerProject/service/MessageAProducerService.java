package com.example.rabbitMQWithSpringBootExampleProducerProject.service;

import com.example.rabbitMQWithSpringBootExampleProducerProject.model.MessageA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageAProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageAProducerService.class);

    private static final String QUEUE_A = "${example.rabbitmq.queue.a}";

    @Value("${example.rabbitmq.routingkey.a}")
    private String routingNameA;

    @Value("${example.rabbitmq.exchange}")
    private String exchangeName;


    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendToQueueA(MessageA messageA) {
        LOGGER.info("sendToQueueB: Notification with id: {} is ready to sent to Queue: {}", QUEUE_A, messageA.getId());

        rabbitTemplate.convertAndSend(exchangeName, routingNameA, messageA);
    }
}
