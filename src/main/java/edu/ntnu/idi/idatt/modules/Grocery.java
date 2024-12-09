package edu.ntnu.idi.idatt.modules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a grocery item with its details.
 * <p>
 * The class represents a grocery item which will be added to the FoodStorage and or a Recipe.
 *
 * @author Sindre Larsen Mjøs
 * @version 2.0
 * @since 19.10.2024
 */
public class Grocery {

  public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  /**
   * Variables
   */
  private final String name;
  private final String unit;
  private final double price;
  private final LocalDate expirationDate;
  private double amount;

  /**
   * Constructs a new Grocery item.
   *
   * @param name           the name of the food item
   * @param unit           the unit of the food item (kg or l)
   * @param amount         the amount of the food item
   * @param price          the price of the food item in kr/kg or kr/l
   * @param expirationDate the expiration expiryDate of the food item (DD.MM.YYYY)
   */
  public Grocery(String name, String unit, double amount, double price,
      String expirationDate) {

    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      this.expirationDate = LocalDate.parse(expirationDate, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Expiration date must be in the format DD.MM.YYYY", e);
    }

    verifyName(name);
    verifyAmount(amount);
    verifyPrice(price);
    verifyUnit(unit);

    this.name = name;
    this.unit = unit;
    this.amount = amount;
    this.price = price;
  }

  /**
   * Verifies if a given food name is valid
   *
   * @param name the name to be verified
   * @throws IllegalArgumentException if the name is null or empty
   */
  public static void verifyName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Food name must be non-null");
    }
  }

  /**
   * Verifies if a given expiration date is valid
   *
   * @param expirationDate the expiration date to be verified
   * @throws IllegalArgumentException if the expiration date is null or empty
   */
  public static void verifyExpirationDate(String expirationDate) {
    try {
      LocalDate.parse(expirationDate, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Expiration date must be in the format DD.MM.YYYY", e);
    }
  }

  /**
   * Verifies if a given amount is valid
   *
   * @param amount the amount to be verified
   * @throws IllegalArgumentException if the amount is negative
   */
  public static void verifyAmount(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
  }

  /**
   * Verifies if a given price is valid
   *
   * @param price the price to be verified
   * @throws IllegalArgumentException if the price is negative or zero
   */
  public static void verifyPrice(double price) {
    if (price <= 0) {
      throw new IllegalArgumentException("Price must be a positive number");
    }
  }

  /**
   * Verifies if a given unit is valid
   *
   * @param unit the unit to be verified
   * @throws IllegalArgumentException if the unit is not kg or l
   */
  public static void verifyUnit(String unit) {
    if (!("kg").equals(unit) && !("l").equals(unit)) {
      throw new IllegalArgumentException(
          "SI unit must be kg or l");
    }
  }


  /**
   * Gets the name of the food item.
   *
   * @return the name of the food item
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the unit of the food item.
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
   * Gets the total price of the food item.
   *
   * @return the total price of the food item
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

  /**
   * Adds the specified amount to the current amount of the food item.
   *
   * @param amount the amount to be added to the current amount
   * @throws IllegalArgumentException if the amount is negative or zero
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
   * @throws IllegalArgumentException if the amount is negative or zero
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
   * @throws IllegalArgumentException if the date is not in the correct format
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
   * Returns a string representation of the grocery item.
   *
   * @return a string containing the food name, amount, SI unit, price, and expiration date
   */
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return name + ", " + amount + " " + unit + ", " + expirationDate.format(
        formatter);
  }
}