package edu.ntnu.idi.idatt.modules;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a grocery item with its details.
 */
public class Grocery {

  // Variables
  private final String foodName;
  private final String unit; // bare Unit, og ha med en til ting
  private final double price;  // potensielt questionable om dette skal være final og expirationDate
  private final LocalDate expirationDate;
  private double amount;

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
    if (foodName == null || unit == null
        || expirationDate == null) { // TODO exceptionhandling for blank strings
      throw new IllegalArgumentException("Food name, SI unit and expiryDate must be non-null");
    }
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price must be a positive number");
    }
    if (!unit.equals("kg") && !unit.equals("l")) {
      throw new IllegalArgumentException(
          "SI unit must be kg or l"); // potensielt endre til enum eller set og kanskje ha med pieces eller noe sånt
    }
    if (!expirationDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
      throw new IllegalArgumentException(
          "Expiration date must be in the format DD.MM.YYYY and use periods (.) as separators");
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    try {
      this.expirationDate = LocalDate.parse(expirationDate, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Expiration date must be in the format DD.MM.YYYY", e);
    }

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
  public double getPrice() {
    return price * amount;
  }

  /**
   * Gets the expiration date of the food item.
   *
   * @return the expiration date of the food item
   * @throws ParseException if the expiration date is not in the correct format
   */
  public LocalDate getExpirationDate() throws ParseException { // hva er throwgreia her?
    return this.expirationDate; // LocalDate on top
  }

  /**
   * Checks if the food item is still valid based on the current date.
   *
   * @return false if the food item is still valid, true otherwise
   */
  public boolean isExpired() {
    return LocalDate.now().isAfter(this.expirationDate);
  }
  // TODO Legge til metoder for exceptionHandling der det gjøres mer enn en gang. (private metode)

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
    this.amount -= amount;
  }

  /**
   * Returns a string representation of the grocery item.
   *
   * @return a string containing the food name, amount, SI unit, price, and expiration date
   */
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return foodName + ", " + amount + " " + unit + ", " + expirationDate.format(formatter);
  }
}