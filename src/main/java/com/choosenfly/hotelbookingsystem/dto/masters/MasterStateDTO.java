package com.choosenfly.hotelbookingsystem.dto.masters;

public class MasterStateDTO {
	
	    private Long id;

	    private String country;

	    private String name;

	    private String atharvaCode;

	    private String stateCode;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		@Override
		public String toString() {
			return "MasterStateDTO [id=" + id + ", country=" + country + ", name=" + name + ", atharvaCode="
					+ atharvaCode + ", stateCode=" + stateCode + "]";
		}

		
	    

}
