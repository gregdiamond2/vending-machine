package com.techelevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	private static final String PUCHASE_DISPLAY_FEED = "Feed Money";
	private static final String PURCHASE_DISPLAY_SELECT = "Select Product";
	private static final String PURCHASE_DISPLAY_FINAL = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTION = { PUCHASE_DISPLAY_FEED, PURCHASE_DISPLAY_SELECT,
			PURCHASE_DISPLAY_FINAL };
	private static final String PUCHASE_DISPLAY_GO_TO_PURCHASE_MENU = "Exit To Menu";

	private static final String[] FEED_MENU_OPTION = { PUCHASE_DISPLAY_FEED, PURCHASE_DISPLAY_SELECT,
			PURCHASE_DISPLAY_FINAL };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws IOException {
		VendingMachine VM500 = new VendingMachine();
		PurchaseBasket yourCart = new PurchaseBasket();
		LogTheLogger logFile = new LogTheLogger();
		
		logFile.logMethod("Initialize", new BigDecimal("0"), new BigDecimal("0"));
		
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items

				int counter = 0;

				for (String slotID : VM500.getInventory().keySet()) {

					// COME BACK AND FORMAT THIS BRO

					System.out.println(VM500.getInventory().keySet().toArray()[counter] + " "
							+ VM500.getInventory().get(slotID).toArray()[0] + " \nAmount Left "
							+ VM500.getInventory().get(slotID).size());
					counter++;
					
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				while (true) {
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTION,
							"\nCurrent Money Provided: $" + VM500.getBalance());
					if (choice.equals(PUCHASE_DISPLAY_FEED)) {
						BigDecimal amount = menu.getAmountFromUserInput();
						while (amount != null) {
							VM500.addToBalance(amount);
							System.out.println("Your current balance is: $" + VM500.getBalance());
							
							logFile.logMethod("FEED MONEY", amount, VM500.getBalance());
							
							amount = menu.getAmountFromUserInput();
						}
					}
					if (choice.equals(PURCHASE_DISPLAY_SELECT)) {
						System.out.println("What would you like to buy?");
						Scanner userInput = new Scanner(System.in);
						String purchaseKey = userInput.nextLine();
						Item boughtItem = null;
						if(VM500.getInventory().containsKey(purchaseKey)) {
							try {
								
								BigDecimal tempBalance = VM500.getBalance();
								
								boughtItem = VM500.purchaseItem(purchaseKey);
								yourCart.addToBasket(boughtItem);
								System.out.println(boughtItem);
								
								logFile.logMethod(boughtItem.getName(), tempBalance, VM500.getBalance());
								
							} catch (OutOfStockException e) {
								System.out.println(e.getMessage());
								choice.equals(PURCHASE_DISPLAY_SELECT);
							} catch (InsufficientFundsException e) {
								System.out.println(e.getMessage());
								choice.equals(PURCHASE_DISPLAY_SELECT);
							}
						} 
						else {
							System.out.println("Please choose a valid item ID");
							choice.equals(PURCHASE_DISPLAY_SELECT);
						}
						
					}

					if (choice.equals(PURCHASE_DISPLAY_FINAL)) {
						
						BigDecimal tempBalance = VM500.getBalance();
						
						System.out.println(VM500.returnChange());
						
						logFile.logMethod("GIVE CHANGE", tempBalance, VM500.getBalance());
						
						System.out.println(yourCart.consumeBasket());
						break;
					}
				}

			}

		}

	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
		// InventoryReader test = new InventoryReader();
		// try {
		// test.generateInventory();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// System.out.println(VM500.getInventory());
	}
}
