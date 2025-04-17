package com.choosenfly.hotelbookingsystem.dto.blockCheckinCheckout;

import java.time.LocalDateTime;

public class BlockCheckinCheckoutValidityDTO {

	    private Long id;
	    private Long blockId;
		private LocalDateTime validityFrom;
		private LocalDateTime validityTo;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getBlockId() {
			return blockId;
		}
		public void setBlockId(Long blockId) {
			this.blockId = blockId;
		}
		public LocalDateTime getValidityFrom() {
			return validityFrom;
		}
		public void setValidityFrom(LocalDateTime validityFrom) {
			this.validityFrom = validityFrom;
		}
		public LocalDateTime getValidityTo() {
			return validityTo;
		}
		public void setValidityTo(LocalDateTime validityTo) {
			this.validityTo = validityTo;
		}
		@Override
		public String toString() {
			return "BlockCheckinCheckoutValidityDTO [id=" + id + ", blockId=" + blockId + ", validityFrom="
					+ validityFrom + ", validityTo=" + validityTo + "]";
		}
		
		
}
