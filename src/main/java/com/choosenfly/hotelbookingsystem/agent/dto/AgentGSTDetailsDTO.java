package com.choosenfly.hotelbookingsystem.agent.dto;

public class AgentGSTDetailsDTO {
    private String agentClassification;
    private String agentGstIn;
    private String agentProvisionalGstno;
    private String agentCorrespondmail;
    private String agentRegisterstatus;
    private String agentHsncode;
    private String agentStatus;
	public String getAgentClassification() {
		return agentClassification;
	}
	public void setAgentClassification(String agentClassification) {
		this.agentClassification = agentClassification;
	}
	public String getAgentGstIn() {
		return agentGstIn;
	}
	public void setAgentGstIn(String agentGstIn) {
		this.agentGstIn = agentGstIn;
	}
	public String getAgentProvisionalGstno() {
		return agentProvisionalGstno;
	}
	public void setAgentProvisionalGstno(String agentProvisionalGstno) {
		this.agentProvisionalGstno = agentProvisionalGstno;
	}
	public String getAgentCorrespondmail() {
		return agentCorrespondmail;
	}
	public void setAgentCorrespondmail(String agentCorrespondmail) {
		this.agentCorrespondmail = agentCorrespondmail;
	}
	public String getAgentRegisterstatus() {
		return agentRegisterstatus;
	}
	public void setAgentRegisterstatus(String agentRegisterstatus) {
		this.agentRegisterstatus = agentRegisterstatus;
	}
	public String getAgentHsncode() {
		return agentHsncode;
	}
	public void setAgentHsncode(String agentHsncode) {
		this.agentHsncode = agentHsncode;
	}
	public String getAgentStatus() {
		return agentStatus;
	}
	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}
	@Override
	public String toString() {
		return "AgentGSTDetailsDTO [agentClassification=" + agentClassification + ", agentGstIn=" + agentGstIn
				+ ", agentProvisionalGstno=" + agentProvisionalGstno + ", agentCorrespondmail=" + agentCorrespondmail
				+ ", agentRegisterstatus=" + agentRegisterstatus + ", agentHsncode=" + agentHsncode + ", agentStatus="
				+ agentStatus + "]";
	}

    // Getters and setters
    // ...
    
    
}