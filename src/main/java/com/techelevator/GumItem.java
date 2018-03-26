package com.techelevator;

import java.math.BigDecimal;

public class GumItem extends Item {

	public GumItem(String name, BigDecimal price) {
		super(name, price);
	}

	public String makeSound() {
		return "Chew Chew, Yum!";
	}

}
