package com.benz.event.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benz.event.producer.constants.EventProduceConstants;
import com.benz.event.producer.model.Event;
import com.benz.event.producer.service.EventProducerService;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class EventPublisherController.
 */
@RestController
@RequestMapping(EventProduceConstants.PUBLISHER)
@Slf4j
public class EventPublisherController {
	
	/** The event producer service. */
	@Autowired
	EventProducerService eventProducerService;


	/**
	 * Publish event.
	 *
	 * @param event the event
	 * @return the response entity
	 */
	@PostMapping(EventProduceConstants.EVENT)
	public ResponseEntity<String> publishEvent(@RequestBody Event event) {
		log.info("Calling the service to publish the event");
		String result = eventProducerService.sendMessageToQueue(event);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
