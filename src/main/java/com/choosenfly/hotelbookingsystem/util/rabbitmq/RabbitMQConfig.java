package com.choosenfly.hotelbookingsystem.util.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.email}")
	private String emailQueue;
	
	@Value("${rabbitmq.exchange}")
	private String emailExchange;
	
	@Value("${rabbitmq.routingkey}")
	private String emailRoutingKey;
	
	
	
	@Bean
	public Queue emailQueue()
	{
		return new Queue(emailQueue,true);
	}
	
	
	
	
	@Bean
	public TopicExchange emailExchange()
	{
		return new TopicExchange(emailExchange);
	
	}
	
	@Bean
	public Binding binding(Queue emailQueue, TopicExchange emailExchange)
	{
		return BindingBuilder.bind(emailQueue).to(emailExchange).with(emailRoutingKey);
	}
	
	
	
	@Bean
	public MessageConverter jsonMessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
	    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(jsonMessageConverter);
	    return rabbitTemplate;
	}
	
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
	        ConnectionFactory connectionFactory,
	        MessageConverter jsonMessageConverter) {
	    
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory);
	    factory.setMessageConverter(jsonMessageConverter);
	    factory.setDefaultRequeueRejected(false); // Let failed messages go to DLQ if configured
	    return factory;
	}
}
