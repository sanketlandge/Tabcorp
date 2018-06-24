package com.tabcorp.events;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertBetsEvent {
	
	@JsonProperty("DateTime")
	private String dateTime;
	
	@JsonProperty("BetType")
	private String betType;
	
	@JsonProperty("PropNumber")
	private String propNumber;
	
	@JsonProperty("CustomerID")
	private String customerID;
	
	@JsonProperty("Investment")
	private String investment;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {		
		this.dateTime = dateTime;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public String getPropNumber() {
		return propNumber;
	}

	public void setPropNumber(String propNumber) {
		this.propNumber = propNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getInvestment() {
		return investment;
	}

	public void setInvestment(String investment) {
		this.investment = investment;
	}

	@Override
	public String toString() {
		return "PlaceBetsEvent [dateTime=" + dateTime + ", betType=" + betType + ", propNumber=" + propNumber
				+ ", customerID=" + customerID + ", investment=" + investment + "]";
	}
	

}
