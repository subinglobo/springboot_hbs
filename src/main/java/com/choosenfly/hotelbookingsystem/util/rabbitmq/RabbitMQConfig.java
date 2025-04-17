package com.choosenfly.hotelbookingsystem.util.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.email}")
	private String emailQueue;

	@Value("${rabbitmq.exchange}")
	private String emailExchange;

	@Value("${rabbitmq.routingkey}")
	private String emailRoutingKey;

	@Bean
	public Queue emailQueue() {
		
		Map<String, Object> args = new HashMap<String,Object>();
		
		args.put("x-dead-letter-exchange", "dl.xchange");
		args.put("x-dead-letter-routing-key", "dl.routing-key");
		
		return new Queue(emailQueue, true,false,false,args);
	}
	
	@Bean
	public Queue deadLetterQueue() {
		
		return new Queue("dl.queue", true);
	}

	

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(emailExchange);
	}

	
	@Bean
	public DirectExchange deadLetterExchange() {
		return new DirectExchange("dl.xchange");
	}

	
	@Bean
	public Binding binding(Queue emailQueue, DirectExchange exchange) {
		return BindingBuilder.bind(emailQueue).to(exchange).with(emailRoutingKey);
	}
	
	

	@Bean
	public Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
		return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with("dl.routing-key");
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {

		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
			ConnectionFactory connectionFactory, MessageConverter messageConverter) {

		SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
		simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
		simpleRabbitListenerContainerFactory.setMessageConverter(messageConverter);
		simpleRabbitListenerContainerFactory.setDefaultRequeueRejected(false);
		return simpleRabbitListenerContainerFactory;
	}
}
