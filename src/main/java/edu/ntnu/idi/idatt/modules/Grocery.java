package edu.ntnu.idi.idatt.modules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a grocery item with its details.
 */
public class Grocery {

  // Variables
  private final String foodName;
  private final String unit;
  private final double price;
  private final LocalDate expirationDate;
  private double amount;
  public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  /**
   * Constructs a new Grocery item.
   *
   * @param foodName       the name of the food item
   * @param unit           the SI unit of the food item (kg or l)
   * @param amount         the amount of the food item
   * @param price          the price of the food item in kr/kg or kr/l
   * @param expirationDate the expiration expiryDate of the food item
   */
  public Grocery(String foodName, String unit, double amount, double price,
      String expirationDate) {

    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      this.expirationDate = LocalDate.parse(expirationDate, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Expiration date must be in the format DD.MM.YYYY", e);
    }

    verifyName(foodName);
    verifyAmount(amount);
    verifyPrice(price);
    verifyUnit(unit);

    this.foodName = foodName;
    this.unit = unit;
    this.amount = amount;
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
    return unit;
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
  public double getTotalPrice() {
    return price * amount;
  }

  /**
   * Gets the expiration date of the food item.
   *
   * @return the expiration date of the food item
   */
  public LocalDate getExpirationDate() {
    return this.expirationDate;
  }

  public static void verifyName(String foodName) {
    if (foodName == null || foodName.trim().isEmpty()) {
      throw new IllegalArgumentException("Food name must be non-null");
    }
  }

  public static void verifyExpirationDate(String expirationDate) {
    try {
      LocalDate.parse(expirationDate, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Expiration date must be in the format DD.MM.YYYY", e);
    }
  }

  public static void verifyAmount(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
  }

  public static void verifyPrice(double price) {
    if (price < 0) {
      throw new IllegalArgumentException("Price must be a positive number");
    }

  }

  public static void verifyUnit(String unit) {
    if (!("kg").equals(unit) && !("l").equals(unit)) {
      throw new IllegalArgumentException(
          "SI unit must be kg or l"); // potensielt endre til enum eller set og kanskje ha med pieces eller noe sånt
    }
  }

  /**
   * Checks if the food item is still valid based on the current date.
   *
   * @return true if the food item is expired, false otherwise
   */
  public boolean isExpired() {
    return LocalDate.now().isAfter(this.expirationDate);
  }

  /**
   * Checks if the food item expires before the specified date.
   *
   * @param date the date to compare with, in the format DD.MM.YYYY
   * @return true if the food item expires before the specified date, false otherwise
   */
  public boolean expireBefore(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate compareDate;
    try {
      compareDate = LocalDate.parse(date, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Date must be in the format DD.MM.YYYY", e);
    }
    return this.expirationDate.isBefore(compareDate);
  }

  /**
   * Adds the specified amount to the current amount of the food item.
   *
   * @param amount the amount to be added to the current amount
   */
  public void addAmount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount to add must be positive");
    }
    this.amount += amount;
  }

  /**
   * Removes the specified amount to the current amount of the food item.
   *
   * @param amount the amount to be Removed from the current amount
   */
  public void removeAmount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount to remove must be positive");
    }
    if (amount > this.amount) {
      throw new IllegalArgumentException("Amount to remove exceeds current amount");
    }
    this.amount -= amount;
  }

  /**
   * Returns a string representation of the grocery item.
   *
   * @return a string containing the food name, amount, SI unit, price, and expiration date
   */
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return foodName + ", " + amount + " " + unit + ", " + expirationDate.format(
        formatter);
  }
}