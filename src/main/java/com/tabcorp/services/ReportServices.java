package com.tabcorp.services;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.tabcorp.events.BetsSoldPerBetTypeReport;
import com.tabcorp.events.BetsSoldPerHourReport;
import com.tabcorp.events.InvestmentPerBetTypeReport;
import com.tabcorp.events.InvestmentPerCustomerReport;
import com.tabcorp.events.InsertBetsEvent;

@RestController
public interface ReportServices {

	public void insertBets(List<InsertBetsEvent> bets);
	
	public InvestmentPerBetTypeReport getInvestmentPerBetType();
	
	public InvestmentPerCustomerReport getInvestmentPerCustomer();
	
	public BetsSoldPerBetTypeReport getBetsSoldByTypeReport();
	
	public BetsSoldPerHourReport getBetsSoldByHour();
	
}
