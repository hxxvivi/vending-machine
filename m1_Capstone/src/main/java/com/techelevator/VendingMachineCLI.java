package com.techelevator;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.techelevator.view.Inventory;
import com.techelevator.view.Menu;

import com.techelevator.Candy;
import com.techelevator.Chips;
import com.techelevator.Drink;
import com.techelevator.Gum;
import com.techelevator.VendingItem;

public class VendingMachineCLI {

	private Menu menu;
	private Inventory inventory;// initiating variable to hold future object


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.inventory = new Inventory();// creating a new object called inventory here

	}

	public void run() {
		List<VendingItem> listOfItems = inventory.getVendingItems();
		List<VendingItem> customerPurchaseItems = new ArrayList<>();
		double customerBalance = 0.00;
		try {
			File transactionLog = new File("Log.txt");
			if(!transactionLog.exists()) {
				transactionLog.createNewFile();
			}
			PrintWriter pw = new PrintWriter(transactionLog);
			DateFormat dateFormat = new SimpleDateFormat();
			Date date = new Date();


			String userInput = menu.getChoiceFromMainMenu();
			while (!userInput.equals("2")) {
				if (userInput.equals("1")) {
					// call the method getItems on inventory
					for (int i = 0; i < listOfItems.size(); i++) {
						System.out.print(listOfItems.get(i).getCode() + "| ");
						System.out.print(listOfItems.get(i).getName() + "| ");
						System.out.println(listOfItems.get(i).getPrice());
					}
				} else {
					System.out.println("Invalid selection! Please try again.");
				}
				userInput = menu.getChoiceFromMainMenu();
			}

			// waitForPurchaseSelection(listOfItems);

			// call purchaseMenu
			String purchaseInput= menu.getPurchaseMenu();
			while (!purchaseInput.equals("3")) { 
				if (purchaseInput.equals("1")) {

					// feed money

					String getMoneyFeed = menu.getMoneyFeed();
					while (!getMoneyFeed.equals("5")) {
						if (getMoneyFeed.equals("1")) {
							customerBalance += 1;
							pw.println(dateFormat.format(date) + " FEED MONEY: " + "$1.00" + "  $" + customerBalance);
							System.out.println("Your balance is: $" + customerBalance);
						} else if (getMoneyFeed.equals("2")) {
							customerBalance += 2;
							pw.println(dateFormat.format(date) + " FEED MONEY: " + "$2.00" + "  $" + customerBalance);
							System.out.println("Your balance is: $" + customerBalance);
						} else if (getMoneyFeed.equals("3")) {
							customerBalance += 5;
							pw.println(dateFormat.format(date) + " FEED MONEY: " + "$5.00" + "  $" + customerBalance);
							System.out.println("Your balance is: $" + customerBalance);
						} else if (getMoneyFeed.equals("4")) {
							customerBalance += 10;
							pw.println(dateFormat.format(date) + " FEED MONEY: " + "$10.00" + "  $" + customerBalance);
							System.out.println("Your balance is: $" + customerBalance);
						} else {
							System.out.println("Invalid selection. Please try again.");
						}
						getMoneyFeed = menu.getMoneyFeed();
					} 
					// end of feed money
					// beginning of select product

				} else if (purchaseInput.equals("2")) {
					// select product
					String getProductCode = menu.getProductCode();
					Boolean codeExist = false;
					for (int i = 0; i < listOfItems.size(); i++) {   
						if(getProductCode.equals(listOfItems.get(i).getCode()))  {
							codeExist = true;
							String itemName = listOfItems.get(i).getName();
							double itemPrice = listOfItems.get(i).getPrice();
							int itemQuantity = listOfItems.get(i).getQuantity();
							///////////////////
							boolean inStock = false;
							if(itemQuantity > 0) {
								inStock = true;
							} else {
								System.out.println("Item out of stock! Please select other items.");
							}
							boolean enoughMoney = false;
							if(itemPrice <= customerBalance) {
								enoughMoney = true;		
								NumberFormat formatter = NumberFormat.getCurrencyInstance();
								customerBalance = customerBalance - itemPrice;
								//update Inventory quantity
								listOfItems.get(i).setQuantity(listOfItems.get(i).getQuantity() - 1);
								

								//update Customer inventory
								String code = listOfItems.get(i).getCode();
								String name = listOfItems.get(i).getName();
								String price = Double.toString(listOfItems.get(i).getPrice());
								String type = listOfItems.get(i).getType();

								if(type.equals("Chip")) {
									customerPurchaseItems.add(new Chips(code, name, price, type, "1"));
								} else if (type.equals("Candy")) {
									customerPurchaseItems.add(new Candy(code, name, price, type, "1"));
								} else if (type.equals("Drink")) {
									customerPurchaseItems.add(new Drink(code, name, price, type, "1"));
								} else if (type.equals("Gum")) {
									customerPurchaseItems.add(new Gum(code, name, price, type, "1"));
								}


								//TODO: Log purchase
								System.out.println("The item you purchased was " + itemPrice + ", your remaining balance is " + formatter.format(customerBalance));
							} else {
								System.out.println("Not enough money, please (1)Feed Money or (2)Select other Product: ");
							}
						}
					}
					if (codeExist == false) {
						System.out.println("Code invalid, please make other selection.");
					}

				} else {
					System.out.println("Selection not valid, please choose again:");
				}
				purchaseInput= menu.getPurchaseMenu();
			}

			//End Transaction menu
			for(int i = 0; i < customerPurchaseItems.size(); i++) {
				pw.print(dateFormat.format(date) + " " + customerPurchaseItems.get(i).getName() + " " + customerPurchaseItems.get(i).getCode() +  ": " 
					+ "  $" + (customerBalance + customerPurchaseItems.get(i).getPrice()) + " $" + customerBalance);
				pw.println();
			}
			
			//Write on sales report
			SalesReportFile.writeReportEntry(customerPurchaseItems);

			//print sound
			//TODO only print sound once
			for (int i = 0; i < customerPurchaseItems.size(); i++) {
				if(customerPurchaseItems.get(i).getType().equals("Chip")) {
					System.out.println("Crunch Crunch, Yum!");
				} else if(customerPurchaseItems.get(i).getType().equals("Candy")) {
					System.out.println("Munch Munch, Yum!");
				} else if(customerPurchaseItems.get(i).getType().equals("Drink")) {
					System.out.println("Glug Glug, Yum!");
				} else if(customerPurchaseItems.get(i).getType().equals("Gum")) {
					System.out.println("Chew Chew, Yum!");
				}
			}

			// Change coins

			int quarter = Math.round((int)((customerBalance * 100)/25));
			customerBalance = customerBalance % 25;
			int nickle = Math.round((int)(customerBalance/10));
			customerBalance = customerBalance % 10;
			int dime = Math.round((int)(customerBalance/5));
			customerBalance = customerBalance % 5;
			int penny = Math.round((int)(customerBalance/1));

			System.out.println("Your change is: " + quarter + " quarters; " + nickle + " nickles; " + dime + " dimes; " + penny + " pennies.");

			pw.flush();
			pw.close();
			//set machine balance to "0"
			customerBalance = 0;

		} catch (Exception e) {

		}
	}

	//Convert money to coin

	public void waitForPurchaseSelection(List<VendingItem> inventoryItems) {
		String userInput = menu.getChoiceFromMainMenu();
		while (!userInput.equals("2")) {
			if (userInput.equals("1")) {
				// call the method getItems on inventory
				for (int i = 0; i < inventoryItems.size(); i++) {
					System.out.print(inventoryItems.get(i).getCode() + "| ");
					System.out.print(inventoryItems.get(i).getName() + "| ");
					System.out.println(inventoryItems.get(i).getPrice());
				}
			} else {
				System.out.println("Invalid selection! Please try again.");
			}
			userInput = menu.getChoiceFromMainMenu();
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		SalesReportFile salesReportFile = new SalesReportFile();
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
