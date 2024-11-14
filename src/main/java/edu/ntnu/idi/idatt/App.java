package edu.ntnu.idi.idatt;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * The main application class that initializes and starts the user interface.
 */
public class App {

  /**
   * The main method that serves as the entry point of the application.
   *
   * @param args the command line arguments
   * @throws ParseException if there is an error during parsing
   */
  public static void main(String[] args) throws ParseException {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
    userInterface.start();
  }
}