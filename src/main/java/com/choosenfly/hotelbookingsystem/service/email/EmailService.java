package com.choosenfly.hotelbookingsystem.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailServiceInterface {

	private final JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendLoginCredentials(String[] toEmail, String username, String password) {
		String subject = "Your Login Credentials";
		String body = getEmailTemplate(username, password);

		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(body, true); // true -> HTML email
			helper.setFrom("reservations@choosenfly.in");
			
			mailSender.send(message);
			System.out.println("Email sent successfully!");
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while sending email");
		}
	}

	private String getEmailTemplate(String username, String password) {
		return "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4;'>"
				+ "<h2 style='color: #164888;'>Login Credentials</h2>" + "<p>Hello,</p>"
				+ "<p>Here are your login details:</p>" + "<p><strong>Username:</strong> " + username + "</p>"
				+ "<p><strong>Password:</strong> " + password + "</p>"
				+ "<p>Login at: <a href='https://b2b.choosenfly.com/app/login'>Click Here</a></p>" + "<br><p>Best Regards,</p>"
				+ "<p>Your Company</p></div>";
	}

}
