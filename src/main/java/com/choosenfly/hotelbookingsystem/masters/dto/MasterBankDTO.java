package com.choosenfly.hotelbookingsystem.masters.dto;

import jakarta.persistence.Column;

public class MasterBankDTO  {
	
	 	private Long bankId;

	    private String name;

	    private Boolean isDeleted;

		public Long getBankId() {
			return bankId;
		}

		public void setBankId(Long bankId) {
			this.bankId = bankId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Boolean getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		@Override
		public String toString() {
			return "MasterBankDTO [bankId=" + bankId + ", name=" + name + ", isDeleted=" + isDeleted + "]";
		}
	    
	    

}
