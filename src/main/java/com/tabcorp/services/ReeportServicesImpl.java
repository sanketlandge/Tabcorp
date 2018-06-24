package com.tabcorp.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tabcorp.domain.Bet;
import com.tabcorp.domain.BetTypes;
import com.tabcorp.domain.Customer;
import com.tabcorp.events.BetsSoldPerBetType;
import com.tabcorp.events.BetsSoldPerBetTypeReport;
import com.tabcorp.events.BetsSoldPerHour;
import com.tabcorp.events.BetsSoldPerHourReport;
import com.tabcorp.events.InsertBetsEvent;
import com.tabcorp.events.InvestmentByPerCustomer;
import com.tabcorp.events.InvestmentPerBetType;
import com.tabcorp.events.InvestmentPerBetTypeReport;
import com.tabcorp.events.InvestmentPerCustomerReport;
import com.tabcorp.exceptions.InvalidBetTypeException;
import com.tabcorp.repository.BetRepository;
import com.tabcorp.repository.CustomerRepository;

@RestController
public class ReeportServicesImpl implements ReportServices {

	private final Logger log = LoggerFactory.getLogger(ReeportServicesImpl.class);
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	BetRepository betRepository;
	
	@Override
	public void insertBets(List<InsertBetsEvent> bets) {
		//only valid bets will be inserted in database
		log.info("[ Inside -- Inserts Bets Method ");
		for(InsertBetsEvent customerBet : bets) {
			Bet bet = new Bet();
			if(BetTypes.contains(customerBet.getBetType())) {
				bet.setBetTypes(customerBet.getBetType());
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				bet.setDateTime(LocalDateTime.parse(customerBet.getDateTime() , dtf));
				bet.setInvestment(Double.parseDouble(customerBet.getInvestment()));
				bet.setPropNumber(Integer.parseInt(customerBet.getPropNumber()));

				Customer customer = new Customer();
				customer.setCustomerId(Integer.parseInt(customerBet.getCustomerID()));
				Set<Bet> betsPerCustomer = new HashSet<Bet>();
				bet.setCustomer(customer);
				betsPerCustomer.add(bet);
				customer.setBets(betsPerCustomer);

				customerRepository.save(customer);
			}else {
				throw new InvalidBetTypeException(customerBet.getBetType());
			}
		}
	}

	@Override
	public InvestmentPerBetTypeReport getInvestmentPerBetType() {
		log.info("[ Processing Start for -- getInvestmentPerBetType ]");
		List<Bet> bets = (List<Bet>) betRepository.findAll();
		Map<String, List<Bet>> betsPerBetType = bets.stream().collect(Collectors.groupingBy(Bet::getBetTypes));

		List<InvestmentPerBetType> everyBetTypeInvestment = new ArrayList<>(); 
		betsPerBetType.entrySet()
		.stream()
		.forEach(x -> {
			String betType = x.getKey();
			List<Bet> betByCustomer = x.getValue();
			double totalInvestment = betByCustomer.stream().mapToDouble(f -> f.getInvestment()).sum();
			everyBetTypeInvestment.add(new InvestmentPerBetType(betType, totalInvestment));
		});
		
		InvestmentPerBetTypeReport betTypeReport = new InvestmentPerBetTypeReport();
		betTypeReport.setReport(everyBetTypeInvestment);
		log.info("[ Processing Ends for -- getInvestmentPerBetType ]");
		return betTypeReport;
	}

	@Override
	public InvestmentPerCustomerReport getInvestmentPerCustomer() {
		log.info("[ Processing Start for -- getInvestmentPerCustomer ]");
		List<Bet> bets = (List<Bet>) betRepository.findAll();
		Map<Customer, List<Bet>> betsPerCustomer = bets.stream().collect(Collectors.groupingBy(Bet::getCustomer));

		List<InvestmentByPerCustomer> everyCustomerInvestment = new ArrayList<>(); 
		betsPerCustomer.entrySet()
		.stream()
		.forEach(x -> {
			Customer custLocal = x.getKey();
			List<Bet> betByCustomer = x.getValue();
			double totalInvestment = betByCustomer.stream().mapToDouble(f -> f.getInvestment()).sum();
			everyCustomerInvestment.add(new InvestmentByPerCustomer(custLocal.getCustomerId(), totalInvestment));
		});
		
		InvestmentPerCustomerReport customerReport = new InvestmentPerCustomerReport();
		customerReport.setInvestmentByCustomerReport(everyCustomerInvestment);
		log.info("[ Processing Ends for -- getInvestmentPerCustomer ]");
		return customerReport;
	}

	@Override
	public BetsSoldPerBetTypeReport getBetsSoldByTypeReport() {
		log.info("[ Processing Start for -- getBetsSoldByTypeReport ]");
		List<Bet> bets = (List<Bet>) betRepository.findAll();
		Map<String, List<Bet>> betsPerBetType = bets.stream().collect(Collectors.groupingBy(Bet::getBetTypes));

		List<BetsSoldPerBetType> betsSoldPerType = new ArrayList<>(); 
		betsPerBetType.entrySet()
		.stream()
		.forEach(x -> {
			betsSoldPerType.add(new BetsSoldPerBetType(x.getKey(), x.getValue().size()));
		});
		
		BetsSoldPerBetTypeReport betTypeReport = new BetsSoldPerBetTypeReport();
		betTypeReport.setBetsSoldPerBetTypes(betsSoldPerType);
		log.info("[ Processing Ends for -- getBetsSoldByTypeReport ]");
		return betTypeReport;
	}

	@Override
	public BetsSoldPerHourReport getBetsSoldByHour() {
		log.info("[ Processing Start for -- getBetsSoldByHour ]");
		List<Bet> bets = (List<Bet>) betRepository.findAll();
		Map<Integer, List<Bet>> betsPerHour = bets.stream().collect(Collectors.groupingBy(b -> b.getDateTime().getHour()));

		List<BetsSoldPerHour> betsSoldPerHour = new ArrayList<>(); 
		betsPerHour.entrySet()
		.stream()
		.forEach(x -> {
			betsSoldPerHour.add(new BetsSoldPerHour(String.valueOf(x.getKey()), x.getValue().size()));
		});
		
		BetsSoldPerHourReport betsPerHourReport = new BetsSoldPerHourReport();
		betsPerHourReport.setBetsPerHour(betsSoldPerHour);
		log.info("[ Processing Ends for -- getBetsSoldByHour ]");
		return betsPerHourReport;
		
	}

}
