package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.UI.UserInterface;

/**
 * The main application class that initializes and starts the user interface.
 * <p>
 * main method serves as the entry point of the application. It creates a new UserInterface object
 * and calls the init() and start() methods on it.
 *
 * @author Sindre Larsen Mjøs
 * @version 2.0
 * @since 19.10.2024
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