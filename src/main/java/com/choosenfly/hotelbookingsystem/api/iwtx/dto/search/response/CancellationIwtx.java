package com.choosenfly.hotelbookingsystem.api.iwtx.dto.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;

@XmlRootElement(name = "Cancellation")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CancellationIwtx {

	@XmlElement
	private int FromDate;
	@XmlElement
	private int ToDate;
	@XmlElement(name = "Value", nillable = true)
    @XmlSchemaType(name = "string")
	private String Value;
	
	private int modifiedFromDate;
	
	
	
	
	@XmlElement
	private Integer NightToCharge;
	
	@XmlElement
	private String PercentOrAmt;
	
	
	
	/**
	 * @return the fromDate
	 */
//	@JsonProperty("FromDate")
//	@XmlAttribute(name = "FromDate")
	
	
	
	public int getFromDate() {
		return FromDate;
	}
	public int getModifiedFromDate() {
		return modifiedFromDate;
	}
	public void setModifiedFromDate(int modifiedFromDate) {
		this.modifiedFromDate = modifiedFromDate;
	}
	public Integer getNightToCharge() {
		return NightToCharge;
	}
	public void setNightToCharge(Integer nightToCharge) {
		NightToCharge = nightToCharge;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(int fromDate) {
		FromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
//	@JsonProperty("ToDate")
//	@XmlAttribute(name = "ToDate")
	public int getToDate() {
		return ToDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(int toDate) {
		ToDate = toDate;
	}

public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	//	@JsonProperty("PercentOrAmt")
//	@XmlAttribute(name = "PercentOrAmt")
	public String getPercentOrAmt() {
		return PercentOrAmt;
	}
	/**
	 * @param percentOrAmt the percentOrAmt to set
	 */
	public void setPercentOrAmt(String percentOrAmt) {
		PercentOrAmt = percentOrAmt;
	}
	@Override
	public String toString() {
		return "Cancellation [FromDate=" + FromDate + ", ToDate=" + ToDate + ", Value=" + Value + ", modifiedFromDate="
				+ modifiedFromDate + ", NightToCharge=" + NightToCharge + ", PercentOrAmt=" + PercentOrAmt + "]";
	}

	
	
	
	
	
}
