package edu.ntnu.iir.bidata;

import java.util.Date;

public class Grocery {

  //variabler
  private final String foodName;
  private final String siUnit;
  private Double amount;
  private final Double price;


  private final Date date;

  //konstruktør
  public Grocery(String foodName, String siUnit, double amount, double price, Date date) {
    this.foodName = foodName;
    this.siUnit = siUnit;
    this.amount = amount;
    this.date = date;
    this.price = price;
  }

  public String getFoodName() {
    return foodName;
  }

  public String getUnit() {
    return siUnit;
  }

  public double getAmount() {
    return amount;
  }

  public double getPrice() {
    return price;
  }

  public boolean getHoldbarhet() {
    Date dateToday = new Date();
    if (date.compareTo(dateToday) > 0) {
      return true;
    } else {
      return false;
    }
  }
}