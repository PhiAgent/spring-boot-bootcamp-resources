package com.ltp.contacts.exception;

public class ContactNotFoundException extends RuntimeException {
  public ContactNotFoundException(String id) {
    super("The id '" + id + "' does not exist in our records");
  }
  // because it's an unchecked runtime exception, there's no need to catch it
  // it'll just be thrown as the application is run
}
