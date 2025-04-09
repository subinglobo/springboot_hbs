package com.choosenfly.hotelbookingsystem.util.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.email.EmailDTO;
import com.choosenfly.hotelbookingsystem.service.email.EmailService;

@Service
public class EmailConsumer {

	private final EmailService emailService;
	
	public EmailConsumer(EmailService emailService)
	{
		this.emailService = emailService;
	}
	
	@RabbitListener(queues = "${rabbitmq.queue.email}")
	public void handleMessage(EmailDTO emailDTO)
	{
		String[] toEmail = emailDTO.getToEmail();
		String username = emailDTO.getUsername();
		String password = emailDTO.getPassword();
		emailService.sendLoginCredentials(toEmail, username, password);
	}
}
