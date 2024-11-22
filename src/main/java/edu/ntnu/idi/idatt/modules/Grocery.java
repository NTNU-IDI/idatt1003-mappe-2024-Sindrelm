package edu.ntnu.idi.idatt.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a grocery item with its details.
 */
public class Grocery {

  // Variables
  private final String foodName;
  private final String siUnit;
  private final Double price;
  private final String expirationDate;
  private Double amount;

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
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price must be a positive number");
    }
    if (!siUnit.equals("kg") & !siUnit.equals("l")) {
      throw new IllegalArgumentException("SI unit must be kg or l");
    }
    if (!expirationDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
      throw new IllegalArgumentException(
          "Expiration date must be in the format DD.MM.YYYY and use periods (.) as separators");
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

  /**
   * Gets the expiration date of the food item.
   *
   * @return the expiration date of the food item
   * @throws ParseException if the expiration date is not in the correct format
   */
  public Date getExpirationDate() throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    return dateFormat.parse(this.expirationDate);
  }

  /**
   * Checks if the food item is still valid based on the current date.
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
   * Checks if the food item expires before the specified date.
   *
   * @param date the date to compare with, in the format DD.MM.YYYY
   * @return true if the food item expires before the specified date, false otherwise
   */
  public boolean expireBefore(String date) {
    try {
      Date currentDate = new Date();
      Date expirationDate = getExpirationDate();
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
      Date compareDate = dateFormat.parse(date);
      return expirationDate.before(compareDate);
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
    return foodName + ", " + amount + " " + siUnit + ", " + expirationDate;
  }
}