package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {
	private String name;
	private BigDecimal price;
	
	public abstract String makeSound();
	
	public Item (String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	
	public String consume() {
	    return makeSound();
	}
	
	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return name + " $" + price;
	}

}
