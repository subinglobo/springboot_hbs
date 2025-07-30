package com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.response;

import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CancellationPolicyDetails")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CancellationPolicyDetailwtx {
	
	@XmlElement
	private CancellationIwtx[] Cancellation;

	/**
	 * @return the cancellation
	 */
//	@JsonProperty("Cancellation")
//	@XmlAttribute(name = "Cancellation")
	public CancellationIwtx[] getCancellation() {
		return Cancellation;
	}

	/**
	 * @param cancellation the cancellation to set
	 */
	public void setCancellation(CancellationIwtx[] cancellation) {
		Cancellation = cancellation;
	}

	@Override
	public String toString() {
		return "CancellationPolicyDetails [Cancellation=" + Arrays.toString(Cancellation) + "]";
	}
	
	
	

}
