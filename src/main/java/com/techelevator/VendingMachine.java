package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Stack;

public class VendingMachine {

	private BigDecimal balance = new BigDecimal(0);

	private Map<String, Stack<Item>> inventory;

	public VendingMachine() throws FileNotFoundException {
		InventoryReader generatingInventory = new InventoryReader();
		inventory = generatingInventory.generateInventory();
	}

	public Item purchaseItem(String slotID) throws OutOfStockException, InsufficientFundsException {
		Item item = null;
		if (inventory.get(slotID).size() > 0) {
			if (balance.compareTo(inventory.get(slotID).peek().getPrice()) >= 0) {
				balance = balance.subtract(inventory.get(slotID).peek().getPrice());
				Item boughtItem = inventory.get(slotID).pop();
				return boughtItem;
			} else {
				throw new InsufficientFundsException("No mo money");
			}
		} else {
			throw new OutOfStockException("Item out of stock");
		}
	}

	public String returnChange() {
		int quarterCount = 0;
		int dimeCount = 0;
		int nickelCount = 0;
		String changeReturned = "";
		while (balance.compareTo(new BigDecimal("0")) > 0) {
			if (balance.compareTo(new BigDecimal("0.25")) >= 0) {
				balance = balance.subtract(new BigDecimal("0.25"));
				++quarterCount;
			} else if (balance.compareTo(new BigDecimal("0.10")) >= 0) {
				balance = balance.subtract(new BigDecimal("0.10"));
				++dimeCount;
			} else if (balance.compareTo(new BigDecimal("0.05")) >= 0) {
				balance = balance.subtract(new BigDecimal("0.05"));
				++nickelCount;
			}
		}
		
		changeReturned = "Your change is: " + quarterCount + " Quarter(s) " + dimeCount + " Dime(s) " + nickelCount
				+ " Nickel(s) \n" + "Your new balance is $" + balance;
		
		return changeReturned;
	}

	public Map<String, Stack<Item>> getInventory() {
		return inventory;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void addToBalance(BigDecimal amountSubmitted) {
		balance = balance.add(amountSubmitted);
	}

}
