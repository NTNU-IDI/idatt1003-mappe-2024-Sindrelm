package edu.ntnu.idi.idatt;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * The UserInterface class initializes the food storage and cookbook, and provides a menu for user
 * interaction.
 */
public class UserInterface {

  public static FoodStorage foodStorage;
  public static CookBook cookBook;

  /**
   * Initializes the food storage and cookbook with some default values.
   */
  public void init() {
    foodStorage = new FoodStorage(new ArrayList<Grocery>());
    cookBook = new CookBook(new ArrayList<Recipe>());

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

    boolean run;
  }

  /**
   * Starts the user interface, displaying the main menu and handling user input.
   *
   * @throws ParseException if there is an error during parsing
   */
  public void start() throws ParseException {
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
        default -> {
          System.out.println("Invalid input");
        }
      }
    }
  }
}