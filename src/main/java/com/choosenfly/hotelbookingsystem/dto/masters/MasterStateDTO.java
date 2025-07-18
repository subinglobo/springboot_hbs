package com.choosenfly.hotelbookingsystem.dto.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class MasterStateDTO {
	
	    private Long id;

	    private Long countryId;

	    private String stateName;

	    private String atharvaCode;

	    private String stateCode;
	    
	    private Boolean isDeleted;
	    
	    private String Country;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getCountryId() {
			return countryId;
		}

		public void setCountryId(Long countryId) {
			this.countryId = countryId;
		}

		public String getStateName() {
			return stateName;
		}

		public void setStateName(String stateName) {
			this.stateName = stateName;
		}

		public String getAtharvaCode() {
			return atharvaCode;
		}

		public void setAtharvaCode(String atharvaCode) {
			this.atharvaCode = atharvaCode;
		}

		public String getStateCode() {
			return stateCode;
		}

		public void setStateCode(String stateCode) {
			this.stateCode = stateCode;
		}

		public Boolean getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public String getCountry() {
			return Country;
		}

		public void setCountry(String country) {
			Country = country;
		}

		@Override
		public String toString() {
			return "MasterStateDTO [id=" + id + ", countryId=" + countryId + ", stateName=" + stateName
					+ ", atharvaCode=" + atharvaCode + ", stateCode=" + stateCode + ", isDeleted=" + isDeleted
					+ ", Country=" + Country + "]";
		}

		

		
		
	    

}
