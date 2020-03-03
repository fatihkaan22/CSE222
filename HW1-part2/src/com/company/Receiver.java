package com.company;

/**
 * Receiver of the shipment
 *
 * @author Fatih Kaan Salgir
 */

public class Receiver extends Customer {
  private String address;

  public Receiver(String name, String surname, String address) {
    super(name, surname);
    this.address = address;
  }

  /**
   * @return address of receiver of shipment
   */
  public String getAddress() {
    return address;
  }
}
