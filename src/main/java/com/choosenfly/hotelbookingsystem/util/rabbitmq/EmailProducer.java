package com.choosenfly.hotelbookingsystem.util.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.email.EmailDTO;

@Service
public class EmailProducer {

	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbitmq.routingkey}")
	private String routingKey;

	private final RabbitTemplate rabbitTemplate;

	public EmailProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(EmailDTO emailDTO) {
		rabbitTemplate.convertAndSend(exchange, routingKey, emailDTO);
	}

}
