package com.company;

import java.util.ArrayList;

/**
 * Transportation personal who responsible for carrying delivering shipments one branch to another or to customer.
 *
 * @author Fatih Kaan Salgir
 */

public class TransportationPersonal extends Worker {
  private ArrayList<Shipment> shipments;
  private Branch destinationBranch;
  private String name;
  private String surname;


  public TransportationPersonal(Branch destinationBranch, String name, String surname, String password) {
    super((name + surname), password, Position.TRANSPORTATION_PERSONAL);
    this.name = name;
    this.surname = surname;
    this.destinationBranch = destinationBranch;
    this.shipments = new ArrayList<>();
  }

  public ArrayList<Shipment> getShipments() {
    return shipments;
  }

  /**
   * @return Destination branch of transportation employee.
   */
  public Branch getDestinationBranch() {
    return destinationBranch;
  }

  /**
   * Add new shipment to shipments array.
   *
   * @param s Shipment to be added into storage.
   */
  public void addShipment(Shipment s) {
    shipments.add(s);
  }

  /**
   * @return Number of shipments
   */
  public int getNoShipment() {
    return shipments.size();
  }

  /**
   * Update the status to delivered of specified shipment.
   *
   * @param s Shipment to be delivered
   */
  public void changeStatusToDelivered(Shipment s) {
    s.setStatus(Shipment.ShipmentStatus.DELIVERED);
    shipments.remove(s);
  }

  public Shipment getShipment(int index) {
    return shipments.get(index);
  }

  /**
   * Transportation personal gives package to branch for further transfers.
   *
   * @param s Shipment to be handed
   * @param b Branch which takes the shipment
   */
  public void handToBranch(Shipment s, Branch b) {
    s.setCurrentBranch(b);
    s.setResponsibleBranchEmp(null);
    s.setResponsibleTransportationPersonal(null);
    shipments.remove(s);
  }

  @Override
  public String toString() {
    return "TransportationPersonal{" +
            "destinationBranch=" + destinationBranch +
            ", noShipment=" + getNoShipment() +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            '}';
  }

  /**
   * Prints information of every shipment in the branch.
   */
  public void listShipments() {
    for (int i = 0; i < getNoShipment(); i++) {
      System.out.println(getShipment(i));
    }
  }
}
