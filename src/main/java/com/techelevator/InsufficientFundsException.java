package com.techelevator;

public class InsufficientFundsException extends Exception{
	
	String message = "";

	public InsufficientFundsException (String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
