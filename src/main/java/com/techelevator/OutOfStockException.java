package com.techelevator;

public class OutOfStockException extends Exception {
	
	String message = "";

	public OutOfStockException (String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
