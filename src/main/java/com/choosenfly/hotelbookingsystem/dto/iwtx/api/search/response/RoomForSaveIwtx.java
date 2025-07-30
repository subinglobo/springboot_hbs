package com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Room")
@XmlAccessorType(XmlAccessType.PROPERTY)

public class RoomForSaveIwtx {
	
	@XmlElement
	private int RoomNo;
	@XmlElement
	private String RoomType;
	@XmlElement
	private int RoomTypeCode;
	@XmlElement
	private String RoomTypeSupplierCode;
	@XmlElement
	private String RoomStatus;
	@XmlElement
	private BlackOut BlackOut;
	@XmlElement
	private String CurrCode;
	@XmlElement
	private String ContractTokenId;
	@XmlElement
	private int RoomConfigurationId;
	@XmlElement
	private String RatePlanCode;
	@XmlElement
	private int RatePlanId;
	@XmlElement
	private String MealPlan;
	@XmlElement
	private int MealPlanCode;
	@XmlElement
	private int NumberOfMeals;
	@XmlElement
	private int RoomNumber;
	
	@XmlElement
	private Double totalRateWithMarkup;
	
	
	//@XmlElement(nillable = true)
	@XmlElement
	private String BuyRate;	
	
	@XmlElement
	private String CommissionSellCharges;
	
	@XmlElement
	private String TaxSellCharges;
	
	@XmlElement
	private double Rate;
	
	@XmlElement
	private String RateDetails;
	
	@XmlElement
	private CancellationPolicyDetailwtx CancellationPolicyDetails;
	@XmlElement
	private String PromotionalContract;
	@XmlElement
	private String PackageYN;
	@XmlElement
	private String NonRefundable;
	@XmlElement
	private String DynamicYN;
	@XmlElement
	private double TotalRate;
	@XmlElement
	private double RateBeforeTax;
	@XmlElement
	private int TotalDiscount;
	@XmlElement
	private boolean RecommendedRetailPrice;
	@XmlElement
	private String ContractLabel;
	
	/**
	 * @return the roomNo
	 */
	
//	@JsonProperty("RoomNo")
//	@XmlAttribute(name = "RoomNo")
	public int getRoomNo() {
		return RoomNo;
	}
	/**
	 * @param roomNo the roomNo to set
	 */
	public void setRoomNo(int roomNo) {
		RoomNo = roomNo;
	}
	/**
	 * @return the roomType
	 */
//	@JsonProperty("RoomType")
//	@XmlAttribute(name = "RoomType")
	public String getRoomType() {
		return RoomType;
	}
	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
		RoomType = roomType;
	}
	/**
	 * @return the roomTypeCode
	 */
//	@JsonProperty("RoomTypeCode")
//	@XmlAttribute(name = "RoomTypeCode")
	public int getRoomTypeCode() {
		return RoomTypeCode;
	}
	/**
	 * @param roomTypeCode the roomTypeCode to set
	 */
	public void setRoomTypeCode(int roomTypeCode) {
		RoomTypeCode = roomTypeCode;
	}
	/**
	 * @return the roomTypeSupplierCode
	 */
//	@JsonProperty("RoomTypeSupplierCode")
//	@XmlAttribute(name = "RoomTypeSupplierCode")
	public String getRoomTypeSupplierCode() {
		return RoomTypeSupplierCode;
	}
	/**
	 * @param roomTypeSupplierCode the roomTypeSupplierCode to set
	 */
	public void setRoomTypeSupplierCode(String roomTypeSupplierCode) {
		RoomTypeSupplierCode = roomTypeSupplierCode;
	}
	/**
	 * @return the roomStatus
	 */
//	@JsonProperty("RoomStatus")
//	@XmlAttribute(name = "RoomStatus")
	public String getRoomStatus() {
		return RoomStatus;
	}
	/**
	 * @param roomStatus the roomStatus to set
	 */
	public void setRoomStatus(String roomStatus) {
		RoomStatus = roomStatus;
	}
	/**
	 * @return the blackOut
	 */
//	@JsonProperty("BlackOut")
//	@XmlAttribute(name = "BlackOut")
	public BlackOut getBlackOut() {
		return BlackOut;
	}
	/**
	 * @param blackOut the blackOut to set
	 */
	public void setBlackOut(BlackOut blackOut) {
		BlackOut = blackOut;
	}
	/**
	 * @return the currCode
	 */
//	@JsonProperty("CurrCode")
//	@XmlAttribute(name = "CurrCode")
	public String getCurrCode() {
		return CurrCode;
	}
	/**
	 * @param currCode the currCode to set
	 */
	public void setCurrCode(String currCode) {
		CurrCode = currCode;
	}
	/**
	 * @return the contractTokenId
	 */
//	@JsonProperty("ContractTokenId")
//	@XmlAttribute(name = "ContractTokenId")

	
	/**
	 * @return the roomConfigurationId
	 */
//	@JsonProperty("RoomConfigurationId")
//	@XmlAttribute(name = "RoomConfigurationId")
	public int getRoomConfigurationId() {
		return RoomConfigurationId;
	}
	public String getContractTokenId() {
		return ContractTokenId;
	}
	public void setContractTokenId(String contractTokenId) {
		ContractTokenId = contractTokenId;
	}
	/**
	 * @param roomConfigurationId the roomConfigurationId to set
	 */
	public void setRoomConfigurationId(int roomConfigurationId) {
		RoomConfigurationId = roomConfigurationId;
	}
	/**
	 * @return the ratePlanCode
	 */
//	@JsonProperty("RatePlanCode")
//	@XmlAttribute(name = "RatePlanCode")
	public String getRatePlanCode() {
		return RatePlanCode;
	}
	/**
	 * @param ratePlanCode the ratePlanCode to set
	 */
	public void setRatePlanCode(String ratePlanCode) {
		RatePlanCode = ratePlanCode;
	}
	/**
	 * @return the ratePlanId
	 */
//	@JsonProperty("RatePlanId")
//	@XmlAttribute(name = "RatePlanId")
	public int getRatePlanId() {
		return RatePlanId;
	}
	/**
	 * @param ratePlanId the ratePlanId to set
	 */
	public void setRatePlanId(int ratePlanId) {
		RatePlanId = ratePlanId;
	}
	/**
	 * @return the mealPlan
	 */
//	@JsonProperty("MealPlan")
//	@XmlAttribute(name = "MealPlan")
	public String getMealPlan() {
		return MealPlan;
	}
	/**
	 * @param mealPlan the mealPlan to set
	 */
	public void setMealPlan(String mealPlan) {
		MealPlan = mealPlan;
	}
	/**
	 * @return the mealPlanCode
	 */
//	@JsonProperty("MealPlanCode")
//	@XmlAttribute(name = "MealPlanCode")
	public int getMealPlanCode() {
		return MealPlanCode;
	}
	/**
	 * @param mealPlanCode the mealPlanCode to set
	 */
	public void setMealPlanCode(int mealPlanCode) {
		MealPlanCode = mealPlanCode;
	}
	/**
	 * @return the numberOfMeals
	 */
//	@JsonProperty("NumberOfMeals")
//	@XmlAttribute(name = "NumberOfMeals")
	public int getNumberOfMeals() {
		return NumberOfMeals;
	}
	/**
	 * @param numberOfMeals the numberOfMeals to set
	 */
	public void setNumberOfMeals(int numberOfMeals) {
		NumberOfMeals = numberOfMeals;
	}
	/**
	 * @return the roomNumber
	 */
//	@JsonProperty("RoomNumber")
//	@XmlAttribute(name = "RoomNumber")
	public int getRoomNumber() {
		return RoomNumber;
	}
	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(int roomNumber) {
		RoomNumber = roomNumber;
	}
	

	
	public String getBuyRate() {
		return BuyRate;
	}
	public void setBuyRate(String buyRate) {
		BuyRate = buyRate;
	}
	public String getCommissionSellCharges() {
		return CommissionSellCharges;
	}
	public void setCommissionSellCharges(String commissionSellCharges) {
		CommissionSellCharges = commissionSellCharges;
	}
	public String getTaxSellCharges() {
		return TaxSellCharges;
	}
	public void setTaxSellCharges(String taxSellCharges) {
		TaxSellCharges = taxSellCharges;
	}
	public String getRateDetails() {
		return RateDetails;
	}
	public void setRateDetails(String rateDetails) {
		RateDetails = rateDetails;
	}
	/**
	 * @return the rate
	 */
//	@JsonProperty("Rate")
//	@XmlAttribute(name = "Rate")
	public double getRate() {
		return Rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		Rate = rate;
	}
	
	/**
	 * @return the cancellationPolicyDetails
	 */
//	@JsonProperty("CancellationPolicyDetails")
//	@XmlAttribute(name = "CancellationPolicyDetails")
	public CancellationPolicyDetailwtx getCancellationPolicyDetails() {
		return CancellationPolicyDetails;
	}
	/**
	 * @param cancellationPolicyDetails the cancellationPolicyDetails to set
	 */
	public void setCancellationPolicyDetails(CancellationPolicyDetailwtx cancellationPolicyDetails) {
		CancellationPolicyDetails = cancellationPolicyDetails;
	}
	/**
	 * @return the promotionalContract
	 */
//	@JsonProperty("PromotionalContract")
//	@XmlAttribute(name = "PromotionalContract")
	public String getPromotionalContract() {
		return PromotionalContract;
	}
	/**
	 * @param promotionalContract the promotionalContract to set
	 */
	public void setPromotionalContract(String promotionalContract) {
		PromotionalContract = promotionalContract;
	}
	/**
	 * @return the packageYN
	 */
//	@JsonProperty("PackageYN")
//	@XmlAttribute(name = "PackageYN")
	public String getPackageYN() {
		return PackageYN;
	}
	/**
	 * @param packageYN the packageYN to set
	 */
	public void setPackageYN(String packageYN) {
		PackageYN = packageYN;
	}
	/**
	 * @return the nonRefundable
	 */
//	@JsonProperty("NonRefundable")
//	@XmlAttribute(name = "NonRefundable")
	public String getNonRefundable() {
		return NonRefundable;
	}
	/**
	 * @param nonRefundable the nonRefundable to set
	 */
	public void setNonRefundable(String nonRefundable) {
		NonRefundable = nonRefundable;
	}
	/**
	 * @return the dynamicYN
	 */
//	@JsonProperty("DynamicYN")
//	@XmlAttribute(name = "DynamicYN")
	public String getDynamicYN() {
		return DynamicYN;
	}
	/**
	 * @param dynamicYN the dynamicYN to set
	 */
	public void setDynamicYN(String dynamicYN) {
		DynamicYN = dynamicYN;
	}
	/**
	 * @return the totalRate
	 */
//	@JsonProperty("TotalRate")
//	@XmlAttribute(name = "TotalRate")
	public double getTotalRate() {
		return TotalRate;
	}
	/**
	 * @param totalRate the totalRate to set
	 */
	public void setTotalRate(double totalRate) {
		TotalRate = totalRate;
	}
	/**
	 * @return the rateBeforeTax
	 */
//	@JsonProperty("RateBeforeTax")
//	@XmlAttribute(name = "RateBeforeTax")
	public double getRateBeforeTax() {
		return RateBeforeTax;
	}
	/**
	 * @param rateBeforeTax the rateBeforeTax to set
	 */
	public void setRateBeforeTax(double rateBeforeTax) {
		RateBeforeTax = rateBeforeTax;
	}
	/**
	 * @return the totalDiscount
	 */
//	@JsonProperty("TotalDiscount")
//	@XmlAttribute(name = "TotalDiscount")
	public int getTotalDiscount() {
		return TotalDiscount;
	}
	/**
	 * @param totalDiscount the totalDiscount to set
	 */
	public void setTotalDiscount(int totalDiscount) {
		TotalDiscount = totalDiscount;
	}
	/**
	 * @return the recommendedRetailPrice
	 */
	public boolean isRecommendedRetailPrice() {
		return RecommendedRetailPrice;
	}
	/**
	 * @param recommendedRetailPrice the recommendedRetailPrice to set
	 */
	public void setRecommendedRetailPrice(boolean recommendedRetailPrice) {
		RecommendedRetailPrice = recommendedRetailPrice;
	}
	/**
	 * @return the contractLabel
	 */
//	@JsonProperty("ContractLabel")
//	@XmlAttribute(name = "ContractLabel")
	public String getContractLabel() {
		return ContractLabel;
	}
	/**
	 * @param contractLabel the contractLabel to set
	 */
	public void setContractLabel(String contractLabel) {
		ContractLabel = contractLabel;
	}
	
	
	public Double getTotalRateWithMarkup() {
		return totalRateWithMarkup;
	}
	public void setTotalRateWithMarkup(Double totalRateWithMarkup) {
		this.totalRateWithMarkup = totalRateWithMarkup;
	}
	@Override
	public String toString() {
		return "RoomForSave [RoomNo=" + RoomNo + ", RoomType=" + RoomType + ", RoomTypeCode=" + RoomTypeCode
				+ ", RoomTypeSupplierCode=" + RoomTypeSupplierCode + ", RoomStatus=" + RoomStatus + ", BlackOut="
				+ BlackOut + ", CurrCode=" + CurrCode + ", ContractTokenId=" + ContractTokenId
				+ ", RoomConfigurationId=" + RoomConfigurationId + ", RatePlanCode=" + RatePlanCode + ", RatePlanId="
				+ RatePlanId + ", MealPlan=" + MealPlan + ", MealPlanCode=" + MealPlanCode + ", NumberOfMeals="
				+ NumberOfMeals + ", RoomNumber=" + RoomNumber + ", totalRateWithMarkup=" + totalRateWithMarkup
				+ ", BuyRate=" + BuyRate + ", CommissionSellCharges=" + CommissionSellCharges + ", TaxSellCharges="
				+ TaxSellCharges + ", Rate=" + Rate + ", RateDetails=" + RateDetails + ", CancellationPolicyDetails="
				+ CancellationPolicyDetails + ", PromotionalContract=" + PromotionalContract + ", PackageYN="
				+ PackageYN + ", NonRefundable=" + NonRefundable + ", DynamicYN=" + DynamicYN + ", TotalRate="
				+ TotalRate + ", RateBeforeTax=" + RateBeforeTax + ", TotalDiscount=" + TotalDiscount
				+ ", RecommendedRetailPrice=" + RecommendedRetailPrice + ", ContractLabel=" + ContractLabel + "]";
	}

	
	
	
	
	
	
}
