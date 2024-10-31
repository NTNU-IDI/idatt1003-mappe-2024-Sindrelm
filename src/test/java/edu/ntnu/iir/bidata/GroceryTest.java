package edu.ntnu.iir.bidata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GroceryTest {

  @Test
  void getPrice() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    double price = grocery.getPrice();
    assertEquals(price, 10.0);
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
  void testIsExpired() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertTrue(grocery.isExpired());
  }

  @Test
  void IsNotExpired() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2025");
    assertFalse(grocery.isExpired());
  }
}

