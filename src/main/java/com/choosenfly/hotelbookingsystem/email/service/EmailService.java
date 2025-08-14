package com.choosenfly.hotelbookingsystem.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.auth.exceptions.EmailSendException;

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
        String body = buildLoginCredentialsTemplate(username, password);
        sendHtmlEmail(toEmail, subject, body);
    }

    public void sendOtpEmail(String[] toEmail, String otp) {
        String subject = "Your OTP Verification Code";
        String body = buildOtpTemplate(otp);
        sendHtmlEmail(toEmail, subject, body);
    }

    private void sendHtmlEmail(String[] toEmails, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmails);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom("reservations@choosenfly.in");

            mailSender.send(message);
            System.out.println("Email sent successfully to: " + String.join(", ", toEmails));
        } catch (MessagingException e) {
            throw new EmailSendException("Error while sending email to: " + String.join(", ", toEmails));
        }
    }

    private String buildLoginCredentialsTemplate(String username, String password) {
        return """
            <div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4;'>
                <h2 style='color: #164888;'>Login Credentials</h2>
                <p>Hello,</p>
                <p>Here are your login details:</p>
                <p><strong>Username:</strong> %s</p>
                <p><strong>Password:</strong> %s</p>
                <p>Login at: <a href='https://b2b.choosenfly.com/app/login'>Click Here</a></p>
                <br><p>Best Regards,</p>
                <p>Your Company</p>
            </div>
            """.formatted(username, password);
    }

    private String buildOtpTemplate(String otp) {
        return """
            <div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4;'>
                <h2 style='color: #164888;'>OTP Verification</h2>
                <p>Hello,</p>
                <p>Your OTP code is:</p>
                <h3 style='color: #FF5733;'>%s</h3>
                <p>This OTP is valid for the next 5 minutes. Please do not share it with anyone.</p>
                <br><p>Best Regards,</p>
                <p>Your Company</p>
            </div>
            """.formatted(otp);
    }
}
