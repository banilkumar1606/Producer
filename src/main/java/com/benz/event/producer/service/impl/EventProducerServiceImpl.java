package com.benz.event.producer.service.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benz.event.producer.constants.EventProduceConstants;
import com.benz.event.producer.exception.ProducerServiceException;
import com.benz.event.producer.model.Event;
import com.benz.event.producer.service.EventProducerService;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class EventProducerServiceImpl.
 */
@Service

/** The Constant log. */
@Slf4j
public class EventProducerServiceImpl implements EventProducerService{
	
	/** The template. */
	@Autowired
    private RabbitTemplate template;
	
	/* (non-Javadoc)
	 * @see com.benz.event.producer.service.EventProducerService#sendMessageToQueue(com.benz.event.producer.model.Event)
	 */
	@Override
	public String sendMessageToQueue(Event event) {
		log.info("sending message to queue");
		try {
			template.convertAndSend(EventProduceConstants.EXCHANGE, EventProduceConstants.ROUTING_KEY, event);
		} catch (AmqpException e) {
			throw new ProducerServiceException("Exception occured while sending message to queue");
		}
		return "Success !!";
	}

}
