package com.citi.cashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.citi.cashcard.repository.CardTransactions;


public class Application {


	
	CardTransactions cardTransactions = new CardTransactions();

	public static void main(String[] args) {
		
		List<Callable<String>> transactionList = new ArrayList<>();
		Application application = new Application();
		
		transactionList.addAll(application.cardTransactions.prepareCardTransactionList());
		application.runJobList(transactionList);
		
	}

	public void runJobList(List<Callable<String>> transactionList) {

		ExecutorService executor = Executors.newWorkStealingPool();

		try {
			executor.invokeAll(transactionList)
			        .stream()
			        .map(future -> {
			        	            try {
										return future.get();
									} catch (InterruptedException | ExecutionException e) {
										return e;
									}
			                       })
			        .forEach(System.out::println);
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}

		executor.shutdown();
	}

}
