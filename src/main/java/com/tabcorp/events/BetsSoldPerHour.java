package com.tabcorp.events;

public class BetsSoldPerHour {

	
	private String hourOfTheDay;
	private int totalBets;
	public BetsSoldPerHour(String dateHourOfTheDay, int totalBets) {
		super();
		this.hourOfTheDay = dateHourOfTheDay;
		this.totalBets = totalBets;
	}

	
	public String getDateHourOfTheDay() {
		return hourOfTheDay;
	}
	public void setDateHourOfTheDay(String dateHourOfTheDay) {
		this.hourOfTheDay = dateHourOfTheDay;
	}
	public int getTotalBets() {
		return totalBets;
	}
	public void setTotalBets(int totalBets) {
		this.totalBets = totalBets;
	}
	
}
