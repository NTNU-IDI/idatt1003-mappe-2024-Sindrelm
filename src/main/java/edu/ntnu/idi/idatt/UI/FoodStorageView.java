package edu.ntnu.idi.idatt.UI;

import static edu.ntnu.idi.idatt.UI.UserInterface.foodStorage;

import edu.ntnu.idi.idatt.modules.Grocery;

/**
 * The view class for interacting with the food storage.
 */
public class FoodStorageView {

  /**
   * Displays the menu for the food storage and handles user input.
   */
  public static void menu() {
    boolean runFoodStorageMenu = true;
    while (runFoodStorageMenu) {
      System.out.print("""
          FOOD STORAGE
          0: Close Food Storage
          1: Add Grocery to Food Storage
          2: Remove Grocery from Food Storage
          3: Remove amount of Grocery from Food Storage
          4: Show all Groceries in Food Storage
          5: Show expired Groceries in Food Storage
          6: Show Groceries which expire before a given date
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
          System.out.println("Closing Food Storage ...");
          runFoodStorageMenu = false;
        }
        case 1 -> {
          try {
            System.out.println("Enter name of grocery:");
            String name = System.console().readLine();
            Grocery.verifyName(name);

            System.out.println("Enter SI unit of grocery (kg or l):");
            String unit = System.console().readLine();
            Grocery.verifyUnit(unit);

            System.out.println("Enter amount of grocery:");
            double amount = Double.parseDouble(System.console().readLine());
            Grocery.verifyAmount(amount);

            System.out.println("Enter price of grocery (per kg or per l):");
            double price = Double.parseDouble(System.console().readLine());
            Grocery.verifyPrice(price);

            System.out.println("Enter expiration date of grocery(DD.MM.YYYY):");
            String expirationDate = System.console().readLine();
            Grocery.verifyExpirationDate(expirationDate);

            foodStorage.addGrocery(name, unit, amount, price, expirationDate);
            System.out.println("Grocery " + name + " added to food storage\n");
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
        case 2 -> {
          try {
            System.out.println("Enter name of grocery:");
            String name = System.console().readLine();
            foodStorage.removeGrocery(name);
            System.out.println("Grocery " + name + " removed from food storage\n");
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
        case 3 -> {
          try {
            System.out.println("Enter name of grocery:");
            String name = System.console().readLine();
            Grocery.verifyName(name);

            System.out.println("Enter amount to remove:");
            double amount = Double.parseDouble(System.console().readLine());
            foodStorage.removeGroceryAmount(name, amount);
            System.out.println(
                "Removed " + amount + foodStorage.getGrocery(name).getUnit() + " of " + name
                    + " from the food storage\n");
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
        case 4 -> foodStorage.getAlphabeticallySortedGroceries().forEach(System.out::println);

        case 5 -> foodStorage.getExpiredGroceries().forEach(System.out::println);

        case 6 -> {
          try {
            System.out.println("Enter date to check before(DD.MM.YYYY):");
            String date = System.console().readLine();
            Grocery.verifyExpirationDate(date);

            foodStorage.getGroceriesExpiringBefore(date).forEach(System.out::println);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
      }
    }
  }
}