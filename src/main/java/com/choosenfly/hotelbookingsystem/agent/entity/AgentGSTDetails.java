package com.choosenfly.hotelbookingsystem.agent.entity;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.*;

@Entity
public class AgentGSTDetails extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "agent_classification")
	private String agentClassification;

	@Column(name = "agent_gst_in")
	private String agentGstIn;

	@Column(name = "agent_provisional_gst_no")
	private String agentProvisionalGstno;

	@Column(name = "agent_correspond_mail")
	private String agentCorrespondmail;

	@Column(name = "agent_register_status")
	private String agentRegisterstatus;

	@Column(name = "agent_hsn_code")
	private String agentHsncode;

	@Column(name = "agent_status")
	private String agentStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return "GSTDetails [id=" + id + ", agentClassification=" + agentClassification + ", agentGstIn=" + agentGstIn
				+ ", agentProvisionalGstno=" + agentProvisionalGstno + ", agentCorrespondmail=" + agentCorrespondmail
				+ ", agentRegisterstatus=" + agentRegisterstatus + ", agentHsncode=" + agentHsncode + ", agentStatus="
				+ agentStatus + "]";
	}

}