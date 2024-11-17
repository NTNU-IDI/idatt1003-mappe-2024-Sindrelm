package edu.ntnu.idi.idatt;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import org.junit.jupiter.api.Test;

public class UserInterfaceTest {

  @Test
  public void testInit() {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
  }
}
