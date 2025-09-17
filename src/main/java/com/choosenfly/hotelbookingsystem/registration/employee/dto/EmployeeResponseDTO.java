package com.choosenfly.hotelbookingsystem.registration.employee.dto;

public class EmployeeResponseDTO {

	private Long id;
	
	private String message;
	
	private boolean success;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "EmployeeResponseDTO [id=" + id + ", message=" + message + ", success=" + success + "]";
	}
	
	
}
