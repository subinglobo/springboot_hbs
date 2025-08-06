package com.choosenfly.hotelbookingsystem.agent.dto;

public class AgentResponseDTO {

	private Long id;
	private String personalEmail;

	public AgentResponseDTO(Long id, String personalEmail) {
		this.id = id;
		this.personalEmail = personalEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	@Override
	public String toString() {
		return "AgentResponseDTO [id=" + id + ", personalEmail=" + personalEmail + "]";
	}

}
