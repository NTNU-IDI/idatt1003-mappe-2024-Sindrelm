package edu.ntnu.idi.idatt;

import java.text.ParseException;
import java.util.ArrayList;

public class App {

  public static void main(String[] args) throws ParseException {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
    userInterface.menu();
  }

}
