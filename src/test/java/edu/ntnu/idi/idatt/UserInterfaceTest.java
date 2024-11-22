package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.UI.UserInterface;
import org.junit.jupiter.api.Test;

public class UserInterfaceTest {

  @Test
  public void testInit() {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
  }
}
