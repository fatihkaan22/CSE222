package com.company;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Abstract class which implements only login and position fields
 *
 * @author Fatih Kaan Salgir
 */

abstract class Worker implements Employee {

  /**
   * All possible worker positions in company
   */
  enum Position {
    ADMIN, BRANCH_EMP, TRANSPORTATION_PERSONAL
  }

  private String userName;
  private String encryptedPassword;
  private Position position;

  public Worker(String userName, String password, Position position) {
    this.userName = userName.strip();
    this.encryptedPassword = getEncryptedPassword(password);
    this.position = position;
  }

  /**
   * Encrypts password by using "SHA-256" algorithm
   *
   * @param password password as raw text
   * @return encrypted password
   */
  public static String getEncryptedPassword(String password) {
    MessageDigest messageDigest;
    try {
      messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(password.getBytes());
      return new String(messageDigest.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return password;
    }
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  @Override
  public Position getPosition() {
    return position;
  }
}
