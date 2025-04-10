package com.choosenfly.hotelbookingsystem.util.rabbitmq;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.email.EmailDTO;
import com.choosenfly.hotelbookingsystem.service.email.EmailService;

@Service
public class EmailConsumer {

	private final EmailService emailService;

	public EmailConsumer(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = "${rabbitmq.queue.email}")
	@Retryable(retryFor = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 3000, multiplier = 2))
	public void handleMessage(EmailDTO emailDTO) {
		System.out.println("Inside handleMessage");
		emailService.sendLoginCredentials(emailDTO.getToEmail(), emailDTO.getUsername(), emailDTO.getPassword());

	}

	@Recover
	public void recover(Exception e, EmailDTO emailDTO) {
		System.err.println("All retries failed: " + e.getMessage());
		throw new AmqpRejectAndDontRequeueException("Sending to DLQ", e);

	}

}
