package edu.ntnu.idi.idatt.UI;

import static edu.ntnu.idi.idatt.UI.UserInterface.cookBook;
import static edu.ntnu.idi.idatt.UI.UserInterface.foodStorage;

import edu.ntnu.idi.idatt.modules.Recipe;
import java.util.ArrayList;

/**
 * The view class for interacting with the cookbook.
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
        System.out.println("Invalid input");
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
            String name = System.console().readLine();
            System.out.println("Enter description of recipe:");
            String description = System.console().readLine();
            System.out.println("Enter number of servings:");
            int servings = Integer.parseInt(System.console().readLine());

            Recipe recipe = new Recipe(new ArrayList<>(), name, description, servings);
            System.out.print("Enter the number of ingredients:");
            int numberOfIngredients = Integer.parseInt(System.console().readLine());

            int i = 1;
            while (i < numberOfIngredients + 1) {
              System.out.println("Enter the name of the " + i + ". ingredient:");
              String foodName = System.console().readLine();
              System.out.println("Enter the siUnit of the  " + i + ". ingredient(l or kg):");
              String siUnit = System.console().readLine();
              System.out.println("Enter the amount of the " + i + ". ingredient:");
              double amount = Double.parseDouble(System.console().readLine());
              System.out.println("Enter the price per/kg for the " + i + ". ingredient:");
              double price = Double.parseDouble(System.console().readLine());
              recipe.addGrocery(foodName, siUnit, amount, price);
              i++;
            }

            cookBook.addRecipe(recipe);
            System.out.println("Recipe " + recipe.getShortDescription() + ", added to cookbook\n");
          } catch (Exception e) {
            System.out.println("An error occurred while adding the recipe. Please try again.");
          }
        }
        case 2 -> {
          System.out.println(cookBook.getCookBookRegister());
        }
        case 3 -> {
          try {
            System.out.println("Enter the name of the recipe to open:");
            String recipeName = System.console().readLine();
            System.out.println(cookBook.getRecipe(recipeName).getRecipeInformation());
          } catch (Exception e) {
            System.out.println("The Recipe does not exist, or is misspelled");
          }
        }
        case 4 -> {
          System.out.println(cookBook.getRecipeSuggestions(foodStorage));
        }
      }
    }
  }
}