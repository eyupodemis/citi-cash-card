package com.citi.cashcard.repository;

import lombok.Data;

@Data
public class Customer {
	
    private Integer customerId;
	
    private String name;
	
    private String surname;
	
    private String address;
	
    private String email;
	
	private String cardNumber;
}
