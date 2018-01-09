package com.citi.cashcard.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CardTransactions {
	
	private List<Customer> customerList = new ArrayList<>();
	private Map<String, CashCard> cashCardList = new HashMap<String, CashCard>();
	
	public List<Callable<String>> prepareCardTransactionList() {

		// Prepare Customer 1
		Customer customer1 = new Customer();
		customer1.setCustomerId(1);
		customer1.setName("Jacob");
		customer1.setSurname("Green");
		customer1.setAddress("Reading");
		customer1.setEmail("jacob.green@gmail.com");
		customerList.add(customer1);

		CashCard cashCard1 = new CashCard();
		cashCard1.setCardNumber("1111222233334444");
		cashCard1.setCustomerId(customer1.getCustomerId());
		cashCard1.setCustomerFullName(customer1.getName() + " " + customer1.getSurname());
		cashCard1.deposit(new BigDecimal("250"));
		cashCardList.put(cashCard1.getCardNumber(), cashCard1);

		// Prepare Customer 2
		Customer customer2 = new Customer();
		customer2.setCustomerId(2);
		customer2.setName("John");
		customer2.setSurname("White");
		customer2.setAddress("London");
		customer2.setEmail("john.white@gmail.com");
		customerList.add(customer2);

		CashCard cashCard2 = new CashCard();
		cashCard2.setCardNumber("5555666677778888");
		cashCard2.setCustomerId(customer2.getCustomerId());
		cashCard2.setCustomerFullName(customer2.getName() + " " + customer2.getSurname());
		cashCard2.deposit(new BigDecimal("105.00"));
		cashCardList.put(cashCard2.getCardNumber(), cashCard2);
		
		List<Callable<String>> jobList = Arrays.asList(
				() -> { cashCardList.get("1111222233334444").deposit(new BigDecimal("13.11")); 
				        return cashCardList.get("1111222233334444").getMessage();},
				() -> { cashCardList.get("1111222233334444").deposit(new BigDecimal("6.89"));
				        return cashCardList.get("1111222233334444").getMessage();},
				() -> { cashCardList.get("1111222233334444").withdraw(new BigDecimal("185.27"));
			        	return cashCardList.get("1111222233334444").getMessage();},
				() -> { cashCardList.get("1111222233334444").withdraw(new BigDecimal("33.15"));
					    return cashCardList.get("1111222233334444").getMessage();},
				() -> { cashCardList.get("1111222233334444").withdraw(new BigDecimal("47.89"));
						return cashCardList.get("1111222233334444").getMessage();},
				() -> { cashCardList.get("1111222233334444").withdraw(new BigDecimal("175.69"));
						return cashCardList.get("1111222233334444").getMessage();},
				() -> { cashCardList.get("5555666677778888").withdraw(new BigDecimal("32.34"));
						return cashCardList.get("5555666677778888").getMessage();},
				() -> { cashCardList.get("5555666677778888").withdraw(new BigDecimal("66.21"));
						return cashCardList.get("5555666677778888").getMessage();},
				() -> { cashCardList.get("5555666677778888").deposit(new BigDecimal("112.85"));
						return cashCardList.get("5555666677778888").getMessage();},
				() -> { cashCardList.get("5555666677778888").deposit(new BigDecimal("0.65"));
						return cashCardList.get("5555666677778888").getMessage();},
				() -> { cashCardList.get("5555666677778888").withdraw(new BigDecimal("46.98"));
						return cashCardList.get("5555666677778888").getMessage();},
				() -> { cashCardList.get("5555666677778888").withdraw(new BigDecimal("76.00"));
						return cashCardList.get("5555666677778888").getMessage();},
				() -> { cashCardList.get("5555666677778888").withdraw(new BigDecimal("91.91"));
						return cashCardList.get("5555666677778888").getMessage();}
				);
	     return jobList;
	}
}
