package edu.ntnu.iir.bidata;

import java.util.ArrayList;

/**
 * Represents a recipe with a list of groceries, a short description, a method, and the number of
 * portions.
 */
public class Recipe {

  private ArrayList<Grocery> groceries;
  private String shortDescription;
  private String method;
  private int portions;

  /**
   * Constructs a new Recipe.
   *
   * @param groceries        the list of groceries required for the recipe
   * @param shortDescription a short description of the recipe
   * @param method           the method or instructions to prepare the recipe
   * @param portions         the number of portions the recipe yields
   */
  public Recipe(ArrayList<Grocery> groceries, String shortDescription, String method,
      int portions) {
    this.groceries = groceries;
    this.shortDescription = shortDescription;
    this.method = method;
    this.portions = portions;
  }

}