package com.company;

/**
 * Transportation personal who responsible for carrying delivering shipments one branch to another or to customer.
 *
 * @author Fatih Kaan Salgir
 */

public class TransportationPersonal extends Worker {
  private DynamicArray<Shipment> shipments;
  private Branch destinationBranch;
  private int noShipment;
  private String name;
  private String surname;


  public TransportationPersonal(Branch destinationBranch, String name, String surname, String password) {
    super((name + surname), password, Position.TRANSPORTATION_PERSONAL);
    this.name = name;
    this.surname = surname;
    this.destinationBranch = destinationBranch;
    this.shipments = new DynamicArray<>();
    this.noShipment = 0;
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
    noShipment++;
  }

  /**
   * @return Number of shipments
   */
  public int getNoShipment() {
    return noShipment;
  }

  /**
   * Update the status to delivered of specified shipment.
   *
   * @param s Shipment to be delivered
   */
  public void changeStatusToDelivered(Shipment s) {
    s.setStatus(Shipment.ShipmentStatus.DELIVERED);
    shipments.remove(s);
    noShipment--;
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
    noShipment--;
  }

  @Override
  public String toString() {
    return "TransportationPersonal{" +
            "destinationBranch=" + destinationBranch +
            ", noShipment=" + noShipment +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            '}';
  }

  /**
   * Prints information of every shipment in the branch.
   */
  public void listShipments() {
    for (int i = 0; i < noShipment; i++) {
      System.out.println(getShipment(i));
    }
  }
}

