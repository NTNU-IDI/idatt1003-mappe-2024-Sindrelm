package edu.ntnu.idi.idatt;

import static edu.ntnu.idi.idatt.UserInterface.foodStorage;

import java.text.ParseException;

public class FoodStorageView {

  public static void menu() throws ParseException {
    boolean runFoodStorageMenu = true;
    while (runFoodStorageMenu) {
      System.out.print("""
          FRIDGE
          What would you like to do?
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
        System.out.println("Invalid input");
        return;
      }

      switch (choice) {
        case 0 -> {
          runFoodStorageMenu = false;
        }
        case 1 -> {
          System.out.println("Enter name of grocery:");
          String name = System.console().readLine();
          System.out.println("Enter SI unit of grocery:");
          String siUnit = System.console().readLine();
          System.out.println("Enter amount of grocery:");
          double amount = Double.parseDouble(System.console().readLine());
          System.out.println("Enter price of grocery:");
          double price = Double.parseDouble(System.console().readLine());
          System.out.println("Enter expiration date of grocery(DD.MM.YYYY):");
          String date = System.console().readLine();

          foodStorage.addGrocery(name, siUnit, amount, price, date);
          System.out.println("Grocery added to food storage");
        }
        case 2 -> {
          System.out.println("Enter name of grocery:");
          String name = System.console().readLine();
          foodStorage.removeGrocery(name);
          System.out.println("Grocery removed from food storage");
        }
        case 3 -> {
          System.out.println("Enter name of grocery:");
          String name = System.console().readLine();
          System.out.println("Enter amount to remove:");
          double amount = Double.parseDouble(System.console().readLine());
          foodStorage.removeGroceryAmount(name, amount);
          System.out.println("Amount removed from grocery in food storage");
        }
        case 4 -> {
          foodStorage.getSortedGroceries().forEach(System.out::println);
        }
        case 5 -> {
          foodStorage.getExpiredGroceries().forEach(System.out::println);
        }
        case 6 -> {
          System.out.println("Enter date to check before(DD.MM.YYYY):");
          String date = System.console().readLine();
          foodStorage.getExpireBefore(date).forEach(System.out::println);
        }
        default -> System.out.println("Invalid input");
      }
    }
  }


}
