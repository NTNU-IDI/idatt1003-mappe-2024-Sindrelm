package edu.ntnu.iir.bidata;

import java.util.ArrayList;

public class CookBook {

  private ArrayList<Recipe> recipes;

  public CookBook(ArrayList<Recipe> recipes) {
    this.recipes = recipes;
  }

  public int getRecipeIndex(String shortDescription) {
    int i = 0;
    while (i < recipes.size()) {
      if (recipes.get(i).getShortDescription().equals(shortDescription)) {
        return i;
      }
      i++;
    }
    throw new IllegalArgumentException("Recipe not found");
  }

  public Recipe getRecipe(String shortDescription) {
    int recipeIndex = getRecipeIndex(shortDescription);
    return recipes.get(recipeIndex);
  }

  public String getCookBookRegister() {
    StringBuilder cookBookRegister = new StringBuilder();
    for (Recipe recipe : recipes) {
      cookBookRegister.append(recipe.getShortDescription()).append("\n");
    }
    return cookBookRegister.toString();
  }

  public void addRecipe(Recipe recipe) {
    this.recipes.add(recipe);
  }

}
