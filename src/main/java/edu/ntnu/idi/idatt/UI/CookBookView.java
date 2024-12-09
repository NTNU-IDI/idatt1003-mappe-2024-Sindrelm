package edu.ntnu.idi.idatt.UI;

import static edu.ntnu.idi.idatt.UI.UserInterface.cookBook;
import static edu.ntnu.idi.idatt.UI.UserInterface.foodStorage;

import edu.ntnu.idi.idatt.modules.Grocery;
import edu.ntnu.idi.idatt.modules.Recipe;
import java.util.ArrayList;

/**
 * The view class for interacting with the cookbook.
 *
 * @author Sindre Larsen Mjøs
 * @version 2.0
 * @since 13.11.2024
 */
public class CookBookView {

  /**
   * Displays the menu for the cookbook and handles user input.
   */
  public static void menu() {

    boolean runCookBook = true;
    while (runCookBook) {
      System.out.print("""
          COOKBOOK
          0: Close CookBook
          1: Add Recipe to CookBook
          2: Show CookBook Register
          3: Open recipe
          4: Show Recipe Suggestions
          """);

      int choice;
      try {
        choice = Integer.parseInt(System.console().readLine());
      } catch (NumberFormatException e) {
        System.out.println(e.getMessage());
        return;
      }

      switch (choice) {
        case 0 -> {
          System.out.println("Closing CookBook ...");
          runCookBook = false;
        }
        case 1 -> {
          try {
            System.out.println("Enter name of recipe:");
            String shortDescription = System.console().readLine();
            Recipe.verifyShortDescription(shortDescription);

            System.out.println("Enter description of recipe:");
            String method = System.console().readLine();
            Recipe.verifyMethod(method);

            System.out.println("Enter number of servings:");
            int numberOfPortions = Integer.parseInt(System.console().readLine());
            Recipe.verifyNumberOfPortions(numberOfPortions);

            Recipe recipe = new Recipe(new ArrayList<>(), shortDescription, method,
                numberOfPortions);

            System.out.print("Enter the number of ingredients:");
            int numberOfIngredients = Integer.parseInt(System.console().readLine());
            if (numberOfIngredients <= 0) {
              throw new IllegalArgumentException(
                  "Number of ingredients must be greater than 0");
            }

            int i = 1;
            while (i < numberOfIngredients + 1) {
              System.out.println("Enter the name of the " + i + ". ingredient:");
              String name = System.console().readLine();
              Grocery.verifyName(name);

              System.out.println("Enter the unit of the  " + i + ". ingredient(l or kg):");
              String unit = System.console().readLine();
              Grocery.verifyUnit(unit);

              System.out.println("Enter the amount of the " + i + ". ingredient:");
              double amount = Double.parseDouble(System.console().readLine());
              Grocery.verifyAmount(amount);

              System.out.println("Enter the price per/kg for the " + i + ". ingredient:");
              double price = Double.parseDouble(System.console().readLine());
              Grocery.verifyPrice(price);

              recipe.addGrocery(name, unit, amount, price);
              i++;
            }

            cookBook.addRecipe(recipe);
            System.out.println("Recipe " + recipe.getShortDescription() + ", added to cookbook\n");
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
        case 2 -> System.out.println(cookBook.getCookBookRegister());

        case 3 -> {
          try {
            System.out.println("Enter the name of the recipe to open:");
            String name = System.console().readLine();
            System.out.println(cookBook.getRecipe(name).getRecipeInformation());
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
        case 4 -> System.out.println(cookBook.getRecipeSuggestions(foodStorage));
      }
    }
  }
}