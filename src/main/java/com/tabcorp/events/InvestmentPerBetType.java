package com.tabcorp.events;

public class InvestmentPerBetType {

	private String betType;
	private double investment;
	
	public InvestmentPerBetType(String betType, double investment) {
		super();
		this.betType = betType;
		this.investment = investment;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public double getInvestment() {
		return investment;
	}
	public void setInvestment(double investment) {
		this.investment = investment;
	}
	
	
}
