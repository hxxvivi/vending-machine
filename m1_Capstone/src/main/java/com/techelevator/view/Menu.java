package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
	
	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
		}
		
	public String getChoiceFromMainMenu() {
		out.println("Please press (1) to Display vending machine items, (2) to make a purchase:");
		out.flush();
		return in.nextLine();
	}
	
	public String getPurchaseMenu() {
		out.println("Please select (1)Feed Money; (2)Select Product; (3)Finish Transaction;");		
		out.flush();
		return in.nextLine();
	}
	
	public String getMoneyFeed() {
		out.println("Please enter amount: (1) $1; (2) $2; (3) $5; (4) $10; (5)End feeding money");
		out.flush();
		return in.nextLine();
	}
	
	public String getProductCode() {
		out.println("Please enter Item Code:");
		out.flush();
		return in.nextLine();
	}
	
}

