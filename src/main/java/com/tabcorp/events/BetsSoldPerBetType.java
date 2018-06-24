package com.tabcorp.events;

public class BetsSoldPerBetType {

	private String betType;
	private long totalBets;
	
	public BetsSoldPerBetType(String betType, long totalBets) {
		super();
		this.betType = betType;
		this.totalBets = totalBets;
	}
	public String getBetType() {
		return betType;
	}
	public void setBetType(String betType) {
		this.betType = betType;
	}
	public long getTotalBets() {
		return totalBets;
	}
	public void setTotalBets(long totalBets) {
		this.totalBets = totalBets;
	}
}
