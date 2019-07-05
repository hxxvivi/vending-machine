package com.techelevator;

public abstract class VendingItem {
	private String name;
	private double price; 
	private String code;
	private String type;
	private int quantity;

	public VendingItem (String code, String name, String price, String type, String quantity) {
		this.name = name;
		this.price = Double.parseDouble(price);
		this.code = code;
		this.type = type;
		this.quantity = Integer.parseInt(quantity);

	}


	public String getCode() {
		return code;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}

	public abstract String getSound();

}
