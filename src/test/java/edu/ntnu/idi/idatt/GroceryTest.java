package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.modules.Grocery;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Grocery class.
 */
class GroceryTest {

  private static Grocery milk;
  private static Grocery bread;
  private static Grocery cheese;

  @BeforeAll
  static void setUp() {
    milk = new Grocery("Milk", "l", 1, 20, "19.02.2025");
    bread = new Grocery("Bread", "kg", 1, 10, "19.02.2024");
    cheese = new Grocery("Cheese", "kg", 1, 30, "19.02.2026");
  }

  @Test
  void testBlankName() {
    //assert
    assertThrows(IllegalArgumentException.class, () -> new Grocery("", "l", 1, 20, "19.02.2025"));
  }

  @Test
  void testInvalidExpirationDate() {
    //assert
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Milk", "l", 1, 20, "1902.2025"));
  }

  @Test
  void testNegativeAmount() {
    //assert
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Milk", "l", -1, 20, "19.02.2025"));
  }

  @Test
  void testNegativePrice() {
    //assert
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Milk", "l", 1, -20, "19.02.2025"));
  }

  @Test
  void testInvalidUnit() {
    //assert
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Milk", "litter", 1, 20, "19.02.2025"));
  }

  @Test
  void testInvalidDate() {
    //assert
    assertThrows(IllegalArgumentException.class,
        () -> new Grocery("Milk", "l", 1, 20, "19.02/2025"));
  }

  @Test
  void testGetName() {
    //arrange
    setUp();
    //act
    String name = milk.getName();
    //assert
    assertEquals("Milk", name);
  }

  @Test
  void testGetUnit() {
    //arrange
    setUp();
    //act
    String unit = cheese.getUnit();
    //assert
    assertEquals("kg", unit);
  }

  @Test
  void testGetAmount() {
    //arrange
    setUp();
    //act
    double amount = bread.getAmount();
    //assert
    assertEquals(1, amount);
  }

  @Test
  void testGetTotalPrice() {
    //arrange
    setUp();
    //act
    double totalPrice = milk.getTotalPrice();
    //assert
    assertEquals(20, totalPrice);
  }

  @Test
  void testGetExpirationDate() {
    //arrange
    setUp();
    //act
    LocalDate expirationDate = milk.getExpirationDate();
    //assert
    assertEquals(LocalDate.of(2025, 2, 19), expirationDate);
  }

  @Test
  void testAddAmount() {
    //arrange
    setUp();
    //act
    milk.addAmount(1);
    double amount = milk.getAmount();
    //assert
    assertEquals(2, amount);
  }

  @Test
  void testAddNegativeAmount() {
    //arrange
    setUp();
    //assert
    assertThrows(IllegalArgumentException.class, () -> milk.addAmount(-1));
  }

  @Test
  void testRemoveAmount() {
    //arrange
    setUp();
    //act
    milk.removeAmount(0.5);
    double amount = milk.getAmount();
    //assert
    assertEquals(0.5, amount);
  }

  @Test
  void testRemoveNegativeAmount() {
    //arrange
    setUp();
    //assert
    assertThrows(IllegalArgumentException.class, () -> milk.removeAmount(-1));
  }

  @Test
  void testRemoveAmountExceedsCurrentAmount() {
    //arrange
    setUp();
    //assert
    assertThrows(IllegalArgumentException.class, () -> milk.removeAmount(2));
  }

  @Test
  void testIsExpired() {
    //arrange
    setUp();
    //act
    boolean isExpired = bread.isExpired();
    //assert
    assertTrue(isExpired);
  }

  @Test
  void testIsNotExpired() {
    //arrange
    setUp();
    //act
    boolean isExpired = cheese.isExpired();
    //assert
    assertFalse(isExpired);
  }

  @Test
  void testExpireBefore() {
    //arrange
    setUp();
    String date = "19.02.2027";
    //act
    boolean expireBefore = milk.expireBefore(date);
    //assert
    assertTrue(expireBefore);
  }

  @Test
  void testExpireAfter() {
    //arrange
    setUp();
    String date = "19.02.2022";
    //act
    boolean expireBefore = milk.expireBefore(date);
    //assert
    assertFalse(expireBefore);
  }
}
