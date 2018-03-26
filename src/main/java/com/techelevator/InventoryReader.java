package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class InventoryReader {

	private static Map<String, Stack<Item>> inventory = new TreeMap<>();

	public Map generateInventory() throws FileNotFoundException {

		File inputFile = getInventoryFile();

		try (Scanner fileScanner = new Scanner(inputFile)) {
			while (fileScanner.hasNextLine()) { // while it can read file until no more new lines
				String line = fileScanner.nextLine();// grab the new line
				String[] itemIDNameAndPrice = line.split("\\|");
				Stack<Item> itemsStack = new Stack<>();
				if (itemIDNameAndPrice[0].startsWith("A")) {
					for (int i = 0; i < 5; i++) {
						itemsStack.push(new ChipItem(itemIDNameAndPrice[1], new BigDecimal(itemIDNameAndPrice[2])));
					}
				} else if (itemIDNameAndPrice[0].startsWith("B")) {
					for (int i = 0; i < 5; i++) {
						itemsStack.push(new CandyItem(itemIDNameAndPrice[1], new BigDecimal(itemIDNameAndPrice[2])));
					}
				} else if (itemIDNameAndPrice[0].startsWith("C")) {
					for (int i = 0; i < 5; i++) {
						itemsStack.push(new DrinkItem(itemIDNameAndPrice[1], new BigDecimal(itemIDNameAndPrice[2])));
					}
				} else if (itemIDNameAndPrice[0].startsWith("D")) {
					for (int i = 0; i < 5; i++) {
						itemsStack.push(new GumItem(itemIDNameAndPrice[1], new BigDecimal(itemIDNameAndPrice[2])));
					}
				}
				inventory.put(itemIDNameAndPrice[0], itemsStack);
			}

		}
		return inventory;
	}

	private static File getInventoryFile() {

		File inventoryFile = new File("vendingmachine.csv");
		if (inventoryFile.exists() == false) { // checks for the existence of a file
			System.out.println("vendingmachine.csv does not exist");
			System.exit(1); // Ends the program
		} else if (inventoryFile.isFile() == false) { // a file is normal if it is not a directory
			System.out.println("vendingmachine.csv is not a file");
			System.exit(1); // Ends the program
		}
		return inventoryFile;
	}

	public static Map<String, Stack<Item>> getInventory() {
		return inventory;
	}

}
