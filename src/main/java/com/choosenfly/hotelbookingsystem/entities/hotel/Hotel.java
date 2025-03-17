package com.choosenfly.hotelbookingsystem.entities.hotel;

import java.util.List;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;
import com.choosenfly.hotelbookingsystem.entities.hotel.linked.LinkedHotelAmenity;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCountry;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCurrency;
import com.choosenfly.hotelbookingsystem.entities.master.MasterHotelCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterHotelType;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarkupType;
import com.choosenfly.hotelbookingsystem.entities.master.MasterPlace;
import com.choosenfly.hotelbookingsystem.entities.master.MasterRegion;
import com.choosenfly.hotelbookingsystem.entities.master.MasterState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel")
public class Hotel extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hotel_id")
	private Long hotelId;

	@Column(name = "hotel_name", nullable = false)
	private String hotelName;

	@ManyToOne
	@JoinColumn(name = "hotel_currency_id")
	private MasterCurrency hotelCurrency;

	@ManyToOne
	@JoinColumn(name = "hotel_category_id")
	private MasterHotelCategory hotelCategory;

	@ManyToOne
	@JoinColumn(name = "hotel_type_id")
	private MasterHotelType hotelType;

	@ManyToOne
	@JoinColumn(name = "markup_type_id")
	private MasterMarkupType markupType;

	@Column(name = "image360")
	private String image360;

	@Column(name = "hotel_description")
	private String hotelDescription;

	@Column(name = "child_com_age_min", nullable = false)
	private Integer childComAgeMin;

	@Column(name = "child_com_age_max", nullable = false)
	private Integer childComAgeMax;

	@Column(name = "child_chargeable_age_min", nullable = false)
	private Integer childChargeableAgeMin;

	@Column(name = "child_chargeable_age_max", nullable = false)
	private Integer childChargeableAgeMax;

	@ManyToOne
	@JoinColumn(name = "region_id", nullable = false)
	private MasterRegion region;

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private MasterCountry country;

	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	private MasterState state;

	@ManyToOne
	@JoinColumn(name = "place_id", nullable = false)
	private MasterPlace place;

	@Column(name = "address")
	private String address;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<HotelContactDetails> contactDetails;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<HotelBankDetails> bankDetails;

	@OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
	private HotelWeekDays weekDays;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<HotelRoom> rooms;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<HotelTermsAndConditions> termsAndConditions;

	@OneToMany(mappedBy = "hotel", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<LinkedHotelAmenity> hotelAmenities;


	
	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public MasterCurrency getHotelCurrency() {
		return hotelCurrency;
	}

	public void setHotelCurrency(MasterCurrency hotelCurrency) {
		this.hotelCurrency = hotelCurrency;
	}

	public MasterHotelCategory getHotelCategory() {
		return hotelCategory;
	}

	public void setHotelCategory(MasterHotelCategory hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public MasterHotelType getHotelType() {
		return hotelType;
	}

	public void setHotelType(MasterHotelType hotelType) {
		this.hotelType = hotelType;
	}

	public MasterMarkupType getMarkupType() {
		return markupType;
	}

	public void setMarkupType(MasterMarkupType markupType) {
		this.markupType = markupType;
	}

	public String getImage360() {
		return image360;
	}

	public void setImage360(String image360) {
		this.image360 = image360;
	}

	public String getHotelDescription() {
		return hotelDescription;
	}

	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}

	public Integer getChildComAgeMin() {
		return childComAgeMin;
	}

	public void setChildComAgeMin(Integer childComAgeMin) {
		this.childComAgeMin = childComAgeMin;
	}

	public Integer getChildComAgeMax() {
		return childComAgeMax;
	}

	public void setChildComAgeMax(Integer childComAgeMax) {
		this.childComAgeMax = childComAgeMax;
	}

	public Integer getChildChargeableAgeMin() {
		return childChargeableAgeMin;
	}

	public void setChildChargeableAgeMin(Integer childChargeableAgeMin) {
		this.childChargeableAgeMin = childChargeableAgeMin;
	}

	public Integer getChildChargeableAgeMax() {
		return childChargeableAgeMax;
	}

	public void setChildChargeableAgeMax(Integer childChargeableAgeMax) {
		this.childChargeableAgeMax = childChargeableAgeMax;
	}

	public MasterRegion getRegion() {
		return region;
	}

	public void setRegion(MasterRegion region) {
		this.region = region;
	}

	public MasterCountry getCountry() {
		return country;
	}

	public void setCountry(MasterCountry country) {
		this.country = country;
	}

	public MasterState getState() {
		return state;
	}

	public void setState(MasterState state) {
		this.state = state;
	}

	public MasterPlace getPlace() {
		return place;
	}

	public void setPlace(MasterPlace place) {
		this.place = place;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<HotelContactDetails> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<HotelContactDetails> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public List<HotelBankDetails> getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(List<HotelBankDetails> bankDetails) {
		this.bankDetails = bankDetails;
	}

	public HotelWeekDays getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(HotelWeekDays weekDays) {
		this.weekDays = weekDays;
	}

	public List<HotelRoom> getRooms() {
		return rooms;
	}

	public void setRooms(List<HotelRoom> rooms) {
		this.rooms = rooms;
	}

	public List<HotelTermsAndConditions> getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(List<HotelTermsAndConditions> termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public List<LinkedHotelAmenity> getHotelAmenities() {
		return hotelAmenities;
	}

	public void setHotelAmenities(List<LinkedHotelAmenity> hotelAmenities) {
		this.hotelAmenities = hotelAmenities;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", hotelCurrency=" + hotelCurrency
				+ ", hotelCategory=" + hotelCategory + ", image360=" + image360 + ", hotelDescription="
				+ hotelDescription + ", childComAgeMin=" + childComAgeMin + ", childComAgeMax=" + childComAgeMax
				+ ", childChargeableAgeMin=" + childChargeableAgeMin + ", childChargeableAgeMax="
				+ childChargeableAgeMax + ", address=" + address + ", zipcode=" + zipcode + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", isDeleted=" + isDeleted + "]";
	}

	
	
	

}