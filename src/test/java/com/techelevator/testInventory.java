package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class testInventory {

	VendingMachine VM500;
	ChipItem chips;

	@Before
	public void setUp() throws Exception {
		VM500 = new VendingMachine();
		VM500.addToBalance(new BigDecimal("10"));
	}

	@Test
	public void testGenerateInventory() {
		assertNotNull(VM500.getInventory());
	}

	@Test
	public void testGetInventory() {
		assertEquals(VM500.getInventory(), VM500.getInventory());
	}

	@Test
	public void testGetA1Name() {
		assertEquals("Potato Crisps", VM500.getInventory().get("A1").peek().getName());
	}

	@Test
	public void testGetA1Price() {
		assertEquals(new BigDecimal("3.05"), VM500.getInventory().get("A1").peek().getPrice());
	}


	@Test
	public void testVendingMachinceBalance() {
		assertEquals(new BigDecimal("10"), VM500.getBalance());
	}

	@Test
	public void testVendingMachineGetChange() {
		assertEquals("Your change is: 40 Quarter(s) 0 Dime(s) 0 Nickel(s) \n" + "Your new balance is $"
				+ new BigDecimal("0.00"), VM500.returnChange());
	}
	
	@Test
	public void testConsumeItems() throws OutOfStockException, InsufficientFundsException {
		List<Item> currentBasket = new ArrayList<Item>();
		PurchaseBasket yourCart = new PurchaseBasket();
		yourCart.addToBasket(VM500.purchaseItem("A1"));
		yourCart.addToBasket(VM500.purchaseItem("A1"));
		assertEquals("Crunch Crunch, Yum!\nCrunch Crunch, Yum!\n", yourCart.consumeBasket());
	}
	
	@Test
	public void testMakeSound() {
		chips = new ChipItem("Name", new BigDecimal("0"));
		assertEquals("Crunch Crunch, Yum!", chips.makeSound());
	}
	
	@Test
	public void testStackDrop() {
		VM500.getInventory().get("A1").pop();
		assertEquals(4, VM500.getInventory().get("A1").size());
	}

}
