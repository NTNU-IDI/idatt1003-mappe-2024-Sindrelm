package edu.ntnu.idi.idatt;

public class CookBookView {

  public void menu() {
    System.out.print("""
        COOKBOOK
        What would you like to do?
        0: Close CookBook
        1: Add Recipe to CookBook
        2: Show all Recipes in CookBook
        3: Search in CookBook
        4: Show Recipe Suggestions
        """);
  }

}
