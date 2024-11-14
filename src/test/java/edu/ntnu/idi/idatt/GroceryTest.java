package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Grocery class.
 */
class GroceryTest {

  /**
   * Tests the getPrice method.
   */
  @Test
  void getPrice() {
    Grocery grocery = new Grocery("Apple", "kg", 2.0, 10.0, "19.02.2004");
    double price = grocery.getPrice();
    assertEquals(price, 20.0);
  }

  /**
   * Tests that an exception is thrown for an invalid SI unit.
   */
  @Test
  void wrongSiUnit() {
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Apple", "mg", 1.0, 10.0, null));
  }

  /**
   * Tests that an exception is thrown for a null food name.
   */
  @Test
  void nullFoodName() {
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery(null, "kg", 1.0, 10.0, null));
  }

  /**
   * Tests that an exception is thrown for a null SI unit.
   */
  @Test
  void nullSiUnit() {
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Apple", null, 1.0, 10.0, null));
  }

  /**
   * Tests that an exception is thrown for a null expiration date.
   */
  @Test
  void nullDate() {
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Apple", "kg", 1.0, 10.0, null));
  }

  /**
   * Tests the getFoodName method.
   */
  @Test
  void getFoodName() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    String foodName = grocery.getFoodName();
    assertEquals(foodName, "Apple");
  }

  /**
   * Tests the getUnit method.
   */
  @Test
  void getUnit() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    String unit = grocery.getUnit();
    assertEquals(unit, "kg");
  }

  /**
   * Tests the getAmount method.
   */
  @Test
  void getAmount() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    double amount = grocery.getAmount();
    assertEquals(amount, 1.0);
  }

  /**
   * Tests the getExpirationDate method.
   *
   * @throws ParseException if there is an error during parsing
   */
  @Test
  void testGetExpirationDate() throws ParseException {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    Date expirationDate = grocery.getExpirationDate();
    assertEquals(expirationDate, new SimpleDateFormat("dd.MM.yyyy").parse("19.02.2004"));
  }

  /**
   * Tests the isExpired method.
   */
  @Test
  void testIsExpired() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertTrue(grocery.isExpired());
  }

  /**
   * Tests the isExpired method for a non-expired grocery.
   */
  @Test
  void IsNotExpired() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2025");
    assertFalse(grocery.isExpired());
  }

  /**
   * Tests the addAmount method.
   */
  @Test
  void testAddAmount() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2025");
    grocery.addAmount(1.0);
    assertEquals(grocery.getAmount(), 2.0);
  }

  /**
   * Tests the toString method.
   */
  @Test
  void testToString() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2025");
    String expected = "Apple, 1.0 kg, 10.0 kr, 19.02.2025";
    assertEquals(grocery.toString(), expected);
  }
}
