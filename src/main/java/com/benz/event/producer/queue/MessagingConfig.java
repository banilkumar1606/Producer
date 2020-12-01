package com.benz.event.producer.queue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.benz.event.producer.constants.EventProduceConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class MessagingConfig.
 */
@Configuration
public class MessagingConfig {
	

	/**
	 * Queue.
	 *
	 * @return the queue
	 */
	@Bean
    public Queue queue() {
        return new Queue(EventProduceConstants.QUEUE);
    }

    /**
     * Exchange.
     *
     * @return the topic exchange
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EventProduceConstants.EXCHANGE);
    }

    /**
     * Binding.
     *
     * @param queue the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(EventProduceConstants.ROUTING_KEY);
    }

    /**
     * Converter.
     *
     * @return the message converter
     */
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Template.
     *
     * @param connectionFactory the connection factory
     * @return the amqp template
     */
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
