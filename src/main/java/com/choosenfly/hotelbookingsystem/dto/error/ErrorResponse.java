package com.choosenfly.hotelbookingsystem.dto.error;

import java.time.LocalDateTime;

public class ErrorResponse {

	private int status;
	private String error;
	private String message;
	private String timestamp;

	public ErrorResponse(int status, String error, String message) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.timestamp = LocalDateTime.now().toString();
	}

	// Getters and setters
	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", error=" + error + ", message=" + message + ", timestamp="
				+ timestamp + "]";
	}

}
