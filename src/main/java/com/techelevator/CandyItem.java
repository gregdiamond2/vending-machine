package com.techelevator;

import java.math.BigDecimal;

public class CandyItem extends Item {

	public CandyItem(String name, BigDecimal price) {
		super(name, price);
	}
	
	public String makeSound() {
		return "Chew Chew, Yum!";
	}

}
