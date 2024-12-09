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

    Recipe pannekakerOppskrift = new Recipe(new ArrayList<>(), "Pannekaker", "Bla, Bla, Bla", 4);
    pannekakerOppskrift.addGrocery("Milk", "l", 0.5, 20);
    pannekakerOppskrift.addGrocery("Flour", "kg", 0.5, 27);
    pannekakerOppskrift.addGrocery("Egg", "kg", 0.6, 50);
    cookBook.addRecipe(pannekakerOppskrift);

    Recipe salatOppskrift = new Recipe(new ArrayList<>(), "Salat", "Bla, Bla, Bla", 4);
    salatOppskrift.addGrocery("Apple", "kg", 0.5, 32);
    salatOppskrift.addGrocery("Mango", "kg", 0.5, 65);
    salatOppskrift.addGrocery("Salatblader", "kg", 0.5, 29);
    cookBook.addRecipe(salatOppskrift);
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