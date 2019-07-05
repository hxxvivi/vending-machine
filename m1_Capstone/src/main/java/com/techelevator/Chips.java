package com.techelevator;

public class Chips extends VendingItem {

public Chips(String code, String name, String price, String type, String quantity) {
    super(code, name, price, type, quantity);
    // TODO Auto-generated constructor stub
}

@Override
public String getSound() {
    // TODO Auto-generated method stub
    return "Crunch Crunch, Yum!";
}
}
