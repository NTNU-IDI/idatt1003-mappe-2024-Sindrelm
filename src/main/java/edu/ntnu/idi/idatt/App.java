package edu.ntnu.idi.idatt;

import java.text.ParseException;
import java.util.ArrayList;

public class App {

  private static FoodStorage foodStorage;
  private static CookBook cookBook;

  public void init() {
    foodStorage = new FoodStorage(new ArrayList<Grocery>());
    cookBook = new CookBook(new ArrayList<Recipe>());
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

  public static void main(String[] args) throws ParseException {
    App app = new App();
    app.start();

  }

}
