package edu.ntnu.idi.idatt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Represents a grocery item with its details.
 */
public class Grocery {

  // Variables
  private final String foodName;
  private final String siUnit;
  private Double amount;
  private final Double price;
  private final String expirationDate;

  /**
   * Constructs a new Grocery item.
   *
   * @param foodName       the name of the food item
   * @param siUnit         the SI unit of the food item (kg or l)
   * @param amount         the amount of the food item
   * @param price          the price of the food item in kr/kg or kr/l
   * @param expirationDate the expiration expiryDate of the food item
   */
  public Grocery(String foodName, String siUnit, double amount, double price,
      String expirationDate) {
    if (foodName == null || siUnit == null || expirationDate == null) {
      throw new IllegalArgumentException("Food name, SI unit and expiryDate must be non-null");
    }
    if (!siUnit.equals("kg") & !siUnit.equals("l")) {
      throw new IllegalArgumentException("SI unit must be kg or l");
    }
    this.foodName = foodName;
    this.siUnit = siUnit;
    this.amount = amount;
    this.expirationDate = expirationDate;
    this.price = price;
  }

  /**
   * Gets the name of the food item.
   *
   * @return the name of the food item
   */
  public String getFoodName() {
    return foodName;
  }

  /**
   * Gets the SI unit of the food item.
   *
   * @return the SI unit of the food item
   */
  public String getUnit() {
    return siUnit;
  }

  /**
   * Gets the amount of the food item.
   *
   * @return the amount of the food item
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Gets the price of the food item.
   *
   * @return the price of the food item
   */
  public double getPrice() {
    return price * amount;
  }

  public Date getExpirationDate() throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    return dateFormat.parse(this.expirationDate);
  }


  /**
   * Checks if the food item is still valid based on the current date.
   * <p>
   * MÅ ENDRES PÅ CATCH DELEN... NBNB
   *
   * @return false if the food item is still valid, true otherwise
   */
  public boolean isExpired() {
    try {
      Date currentDate = new Date();
      Date expirationDate = getExpirationDate();
      return currentDate.after(expirationDate);
    } catch (ParseException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Adds the specified amount to the current amount of the food item.
   *
   * @param amount the amount to be added to the current amount
   */
  public void addAmount(double amount) {
    this.amount += amount;
  }

  /**
   * Returns a string representation of the grocery item.
   *
   * @return a string containing the food name, amount, SI unit, price, and expiration date
   */
  public String toString() {
    return foodName + ", " + amount + " " + siUnit + ", " + price + " kr, " + expirationDate;
  }
}
