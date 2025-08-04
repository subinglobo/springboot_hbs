package com.choosenfly.hotelbookingsystem.email.service;

public interface EmailServiceInterface {

	  void sendLoginCredentials(String[] toEmail, String username, String password);
}
