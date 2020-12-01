package com.benz.event.producer.service;

import com.benz.event.producer.model.Event;

/**
 * The Interface EventProducerService.
 */
public interface EventProducerService{
	
	/**
	 * Send message to queue.
	 *
	 * @param event the event
	 * @return the string
	 */
	public String sendMessageToQueue(Event event);
	
}
