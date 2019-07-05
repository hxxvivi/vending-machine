package com.techelevator;

public class Candy extends VendingItem {

public Candy(String code, String name, String price, String type, String quantity) {
	    super(code, name, price, type, quantity);
    // TODO Auto-generated constructor stub
}

@Override
public String getSound() {
    // TODO Auto-generated method stub
    return "Munch Munch, Yum!";
}
}


