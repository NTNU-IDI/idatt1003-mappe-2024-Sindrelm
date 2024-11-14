package edu.ntnu.idi.idatt;

import java.text.ParseException;
import java.util.ArrayList;

public class UserInterface {

  public static FoodStorage foodStorage;
  public static CookBook cookBook;

  public void init() {
    foodStorage = new FoodStorage(new ArrayList<Grocery>());
    cookBook = new CookBook(new ArrayList<Recipe>());
    boolean run;
  }


  public void start() {
    init();
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

  public void menu() throws ParseException {
    start();
    boolean run = true;
    while (run) {
      System.out.print("""
          What would you like to do?
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
        case 0 -> run = false;
        case 1 -> {
          CookBookView.menu();
        }
        case 2 -> {
          FoodStorageView.menu();
        }
        default -> {
          System.out.println("Invalid choice");
        }
      }
    }
  }
}
