package com.choosenfly.hotelbookingsystem.service.email;

public interface EmailServiceInterface {

	  void sendLoginCredentials(String[] toEmail, String username, String password);
}
