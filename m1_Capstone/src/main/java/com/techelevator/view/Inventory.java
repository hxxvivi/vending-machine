package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.techelevator.Candy;
import com.techelevator.Chips;
import com.techelevator.Drink;
import com.techelevator.Gum;
import com.techelevator.VendingItem;
	

public class Inventory {
	private List<VendingItem> vendingItems;
	
	
	
	public Inventory() {
		
		vendingItems = new ArrayList<>();

		File newFile = new File("vendingmachine.csv");
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(newFile);
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String [] splittedLine = line.split("\\|");
				String code = splittedLine[0];
				String name = splittedLine[1];
				String price = splittedLine[2];
				String type = splittedLine[3];
				String quantity = "5";

				if(type.equals("Chip")) {
					vendingItems.add(new Chips(code, name, price, type, quantity));
				} else if (type.equals("Candy")) {
					vendingItems.add(new Candy(code, name, price, type, quantity));
				} else if (type.equals("Drink")) {
					vendingItems.add(new Drink(code, name, price, type, quantity));
				} else if (type.equals("Gum")) {
					vendingItems.add(new Gum(code, name, price, type, quantity));
				}

			}


		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public VendingItem getPurchaseItem(String code) {
		// If not exist
		return null;
		
	}
	
	public List<VendingItem> getVendingItems() {
		return vendingItems;
	}

}
