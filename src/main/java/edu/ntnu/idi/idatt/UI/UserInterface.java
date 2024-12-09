package edu.ntnu.idi.idatt.UI;

import edu.ntnu.idi.idatt.modules.CookBook;
import edu.ntnu.idi.idatt.modules.FoodStorage;
import edu.ntnu.idi.idatt.modules.Recipe;
import java.util.ArrayList;

/**
 * The UserInterface class initializes the food storage and cookbook, and provides a menu for user
 * interaction.
 * <p>
 * The class also calls on FoodStorageView and CookBookView to display the respective menus.
 *
 * @author Sindre Larsen Mjøs
 * @version 2.0
 * @since 13.11.2024
 */
public class UserInterface {

  public static FoodStorage foodStorage;
  public static CookBook cookBook;

  /**
   * Initializes the food storage and cookbook with some default values.
   */
  public void init() {
    foodStorage = new FoodStorage(new ArrayList<>());
    cookBook = new CookBook(new ArrayList<>());

    foodStorage.addGrocery("Milk", "l", 1, 20, "19.11.2020");
    foodStorage.addGrocery("Egg", "kg", 0.6, 50, "28.11.2024");
    foodStorage.addGrocery("Flour", "kg", 1, 27, "31.12.2028");

    String pancakesMethod = """
        1. In a large mixing bowl, combine 0.5 liters of milk, 0.2 kg of eggs, and 0.5 kg of flour.
        2. Whisk the ingredients together until you have a smooth batter.
        3. Heat a non-stick frying pan over medium heat.
        4. Pour a bit of batter onto the pan and spread it evenly.
        5. Cook until bubbles form on the surface and the edges look set, then flip the pancake.
        6. Cook the other side until golden brown.
        7. Repeat with the remaining batter.
        8. Serve the pancakes warm with your choice of toppings like syrup, fruit, or jam.
        """;
    Recipe pancakeRecipe = new Recipe(new ArrayList<>(), "Pancakes", pancakesMethod, 4);
    pancakeRecipe.addGrocery("Milk", "l", 0.5, 20);
    pancakeRecipe.addGrocery("Flour", "kg", 0.5, 27);
    pancakeRecipe.addGrocery("Egg", "kg", 0.2, 50);
    cookBook.addRecipe(pancakeRecipe);

    String saladMethod = """
        1. Wash 0.5 kg of apples, 0.5 kg of mangoes, and 0.5 kg of salad greens thoroughly.
        2. Chop the apples and mangoes into bite-sized pieces.
        3. Tear the salad greens into smaller pieces if needed.
        4. In a large salad bowl, combine the chopped apples, mangoes, and salad greens.
        5. Toss the ingredients gently to mix them evenly.
        6. Optionally, drizzle with your favorite dressing or add a sprinkle of nuts for extra crunch.
        7. Serve the salad fresh and enjoy.
        """;
    Recipe saladRecipe = new Recipe(new ArrayList<>(), "Salad", saladMethod, 4);
    saladRecipe.addGrocery("Apple", "kg", 0.5, 32);
    saladRecipe.addGrocery("Mango", "kg", 0.5, 65);
    saladRecipe.addGrocery("Salad", "kg", 0.5, 29);
    cookBook.addRecipe(saladRecipe);
  }

  /**
   * Starts the user interface, displaying the main menu and handling user input.
   */
  public void start() {
    init();
    boolean runUserInterface = true;
    while (runUserInterface) {
      System.out.print("""
          MAIN MENU
          0: Close Program
          1: Open CookBook
          2: Open FoodStorage
          """);

      int choice;
      try {
        choice = Integer.parseInt(System.console().readLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input");
        continue;
      }
      switch (choice) {
        case 0 -> {
          System.out.println("Closing program ...");
          runUserInterface = false;
        }
        case 1 -> {
          System.out.println("Opening CookBook ...");
          CookBookView.menu();
        }
        case 2 -> {
          System.out.println("Opening FoodStorage ...");
          FoodStorageView.menu();
        }
      }
    }
  }
}