package com.choosenfly.hotelbookingsystem.hotel.search.dto;

import java.util.List;
import java.util.Map;

public class SearchResponse {

	private Map<String, String> status;
	private List<HotelSearchResult> result;
	private String finalStatus;

	private int page;
	private int size;
	private int totalResults;
	
	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	public Map<String, String> getStatus() {
		return status;
	}

	public void setStatus(Map<String, String> status) {
		this.status = status;
	}

	public List<HotelSearchResult> getResult() {
		return result;
	}

	public void setResult(List<HotelSearchResult> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "SearchResponse [status=" + status + ", result=" + result + ", finalStatus=" + finalStatus + "]";
	}

}
