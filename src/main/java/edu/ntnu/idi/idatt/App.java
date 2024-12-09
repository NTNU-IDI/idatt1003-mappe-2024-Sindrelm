package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.UI.UserInterface;

/**
 * The main application class that initializes and starts the user interface.
 */
public class App {

  /**
   * The main method that serves as the entry point of the application.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
    userInterface.start();
  }
}