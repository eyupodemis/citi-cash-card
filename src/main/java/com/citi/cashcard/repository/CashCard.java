package com.citi.cashcard.repository;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class CashCard {

	@Getter @Setter
	private String cardNumber;

	@Getter @Setter
    private Integer customerId;
	
	@Getter @Setter
	private String customerFullName;

	@Getter @Setter
	private BigDecimal balance = BigDecimal.ZERO;

	@Getter @Setter
	private String message;

	public synchronized Boolean  withdraw(BigDecimal withdrawAmount) {
		
		Boolean result= false;
		
		if (checkAmountIfZeroOrUnder(withdrawAmount)) {
			setMessage("You are trying to withraw " + withdrawAmount.toString()
					+ ". This is under expected minimum amount of money. Total Balance: "+this.getBalance());
		} else {
			if (checkBalance(withdrawAmount)) {
				this.setBalance(this.getBalance().subtract(withdrawAmount));
				setMessage(withdrawAmount + " GBP has been withdrawn from " + cardNumber + " card number. Total Balance: "+this.getBalance());
				result= true;
			} else {
				setMessage("Insufficient balance("+ this.balance+") amount on card " + cardNumber +
						  " you are trying to withdraw : " + withdrawAmount + " GBP ");
			}
		}
		
		return result;
	}

	public synchronized Boolean deposit(BigDecimal depositAmount) {
		
		Boolean result= false;
		
		if (checkAmountIfZeroOrUnder(depositAmount)) {
			setMessage("You are trying to deposit " + depositAmount.toString()
					+ ". This is under expected minimum amount of money. Total Balance: "+this.getBalance() );
		} else {
			this.setBalance(this.getBalance().add(depositAmount));
			setMessage(depositAmount + " GBP has been deposited to " + cardNumber + " card number ");
			result = true;
		}
		return result;
	}

	public Boolean checkBalance(BigDecimal withdrawAmount) {
		return this.balance.compareTo(withdrawAmount) < 0 ? false : true;
	}

	public Boolean checkAmountIfZeroOrUnder(BigDecimal amount) {
		return amount.compareTo(BigDecimal.ZERO) > 0 ? false : true;
	}

}
