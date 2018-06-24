package com.tabcorp.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tabcorp.events.BetsSoldPerBetTypeReport;
import com.tabcorp.events.BetsSoldPerHourReport;
import com.tabcorp.events.InsertBetsEvent;
import com.tabcorp.events.InvestmentPerBetTypeReport;
import com.tabcorp.events.InvestmentPerCustomerReport;
import com.tabcorp.services.ReportServices;

@RestController
public class BetsController {
	
	private final Logger log = LoggerFactory.getLogger(BetsController.class);
	
	@Autowired
	ReportServices reportServices;

	@RequestMapping(value = "/api/bet", method = RequestMethod.POST)
    public String insertBets(@RequestBody List<InsertBetsEvent> bets){
		reportServices.insertBets(bets);
        return "Bets Inserted Successfully";
    }
	
	@RequestMapping(value = "/api/bet/getInvestmentByCustomer", method = RequestMethod.GET)
    public InvestmentPerCustomerReport getInvestmentPerCustomer(){
        return reportServices.getInvestmentPerCustomer();
    }
	
	@RequestMapping(value = "/api/bet/getInvestmentByBetType", method = RequestMethod.GET)
    public  InvestmentPerBetTypeReport getInvestmentPerBetType(){
        return reportServices.getInvestmentPerBetType();
    }
	
	@RequestMapping(value = "/api/bet/getTotalBetsByType", method = RequestMethod.GET)
    public  BetsSoldPerBetTypeReport getTotalBetsByType(){
        return reportServices.getBetsSoldByTypeReport();
    }
	
	@RequestMapping(value = "/api/bet/getTotalBetsPerHour", method = RequestMethod.GET)
    public  BetsSoldPerHourReport getTotalBetsperHour(){
        return reportServices.getBetsSoldByHour();
    }
	
}
