package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class PurchaseBasket {

	private List<Item> currentBasket = new ArrayList<Item>();

	public void addToBasket(Item purchasedItem) {
		currentBasket.add(purchasedItem);
	}

	public String consumeBasket() {
		String consumeSounds = "";
		if (!currentBasket.isEmpty()) {
			for (Item element : currentBasket) {
				consumeSounds = consumeSounds.concat(element.consume()+ "\n");
			}
		}
		
		return consumeSounds;
	}

}
