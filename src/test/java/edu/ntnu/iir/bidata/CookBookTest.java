package edu.ntnu.iir.bidata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class CookBookTest {

  @Test
  public void testGetRecipeIndexNotFound() {
    // Arrange
    CookBook cookBook = new CookBook(new ArrayList<Recipe>());

    // Act
    try {
      cookBook.getRecipeIndex("Short description");
    } catch (IllegalArgumentException e) {
      // Assert
      assertEquals("Recipe not found", e.getMessage());
    }

  }

  @Test
  public void testGetRecipe() {
    // Arrange
    CookBook cookBook = new CookBook(new ArrayList<Recipe>());
    Recipe recipe1 = new Recipe(new ArrayList<Grocery>(), "Short description 1", "Method 1", 1);
    Recipe recipe2 = new Recipe(new ArrayList<Grocery>(), "Short description 2", "Method 2", 2);
    cookBook.addRecipe(recipe1);
    cookBook.addRecipe(recipe2);

    // Act
    Recipe recipe1FromCookBook = cookBook.getRecipe("Short description 1");
    Recipe recipe2FromCookBook = cookBook.getRecipe("Short description 2");

    // Assert
    assertEquals(recipe1, recipe1FromCookBook);
    assertEquals(recipe2, recipe2FromCookBook);
  }

  @Test
  public void testGetCookBookRegister() {
    // Arrange
    CookBook cookBook = new CookBook(new ArrayList<Recipe>());
    Recipe recipe1 = new Recipe(new ArrayList<Grocery>(), "Short description 1", "Method 1", 1);
    Recipe recipe2 = new Recipe(new ArrayList<Grocery>(), "Short description 2", "Method 2", 2);
    cookBook.addRecipe(recipe1);
    cookBook.addRecipe(recipe2);

    // Act
    String cookBookRegister = cookBook.getCookBookRegister();

    // Assert
    assertEquals("Short description 1\nShort description 2\n", cookBookRegister);
  }

}
