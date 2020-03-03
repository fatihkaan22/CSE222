package com.company;

/**
 * Abstract class which consist of name and surname information of customer
 *
 * @author Fatih Kaan Salgir
 */

abstract class Customer {
  private String name;
  private String surname;
  private Integer trackingNumber;

  public Customer(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  public void setTrackingNumber(Integer trackingNumber) {
    this.trackingNumber = trackingNumber;
  }

  /**
   * @return name and surname of customer
   */
  @Override
  public String toString() {
    return "Name='" + name + '\'' +
            ", Surname='" + surname + '\'';
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }
}
