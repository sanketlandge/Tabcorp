package com.tabcorp.events;

public class InvestmentByPerCustomer {

	private int customerId;
	private double investment;
	
	public InvestmentByPerCustomer(int customerId, double investment) {
		super();
		this.customerId = customerId;
		this.investment = investment;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getInvestment() {
		return investment;
	}
	public void setInvestment(double investment) {
		this.investment = investment;
	}
	
}
