package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import org.junit.jupiter.api.Test;

class GroceryTest {

  @Test
  void getPrice() {
    Grocery grocery = new Grocery("Apple", "kg", 2.0, 10.0, "19.02.2004");
    double price = grocery.getPrice();
    assertEquals(price, 20.0);
  }

  @Test
  void wrongSiUnit() {
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Apple", "mg", 1.0, 10.0, null));
  }

  @Test
  void nullFoodName() {
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery(null, "kg", 1.0, 10.0, null));
  }

  @Test
  void nullSiUnit() {
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Apple", null, 1.0, 10.0, null));
  }

  @Test
  void nullDate() {
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Apple", "kg", 1.0, 10.0, null));
  }

  @Test
  void getFoodName() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    String foodName = grocery.getFoodName();
    assertEquals(foodName, "Apple");
  }

  @Test
  void getUnit() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    String unit = grocery.getUnit();
    assertEquals(unit, "kg");
  }

  @Test
  void getAmount() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    double amount = grocery.getAmount();
    assertEquals(amount, 1.0);
  }

  @Test
  void testGetExpirationDate() throws ParseException {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    Date expirationDate = grocery.getExpirationDate();
    assertEquals(expirationDate, new SimpleDateFormat("dd.MM.yyyy").parse("19.02.2004"));
  }

  @Test
  void testIsExpired() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertTrue(grocery.isExpired());
  }

  @Test
  void IsNotExpired() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2025");
    assertFalse(grocery.isExpired());
  }

  @Test
  void testAddAmount() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2025");
    grocery.addAmount(1.0);
    assertEquals(grocery.getAmount(), 2.0);
  }

  @Test
  void testToString() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2025");
    String expected = "Apple, 1.0 kg, 10.0 kr, 19.02.2025";
    assertEquals(grocery.toString(), expected);
  }
}
