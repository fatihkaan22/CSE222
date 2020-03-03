package com.company;

/**
 * Branch employee who responsible for adding new shipments to branch.
 *
 * @author Fatih Kaan Salgir
 */

public class BranchEmployee extends Worker {

  private String name;
  private String surname;
  private Branch workingBranch;
  private CargoCompany workingCompany;

  public BranchEmployee(String name, String surname, Branch workingBranch,
                        CargoCompany workingCompany, String password) {
    super(name + surname, password, Position.BRANCH_EMP);
    this.name = name;
    this.surname = surname;
    this.workingBranch = workingBranch;
    this.workingCompany = workingCompany;
  }


  /**
   * @param s           new shipment to be hand in from customer
   * @param destination destination branch of shipment, see other construction if its local shipment
   */
  public void acceptShipmentFromCustomer(Shipment s, Branch destination) {
    s.setCurrentBranch(workingBranch);
    workingBranch.addShipment(s);
    s.setResponsibleBranchEmp(this);
    s.setDestinationBranch(destination);
  }

  /**
   * @param shipment new shipment to be hand in from customer
   */
  public void acceptShipmentFromCustomer(Shipment shipment) {
    acceptShipmentFromCustomer(shipment, workingBranch);
  }

  /**
   * Adds new custom information in the information part in the shipment.
   * Branch employee can enter any valid text.
   *
   * @param trackingNumber
   * @param infoMessage
   */
  public void addShipmentInformation(int trackingNumber, String infoMessage) {
    workingCompany.getShipmentByTrackingNumber(trackingNumber).
            addInfoMessage(infoMessage, this.name + " " + this.surname);
  }


  /**
   * Adds new customer to company.
   *
   * @param c Customer to be added
   */
  public void addUser(Customer c) {
    workingCompany.addUser(c);
  }

  /**
   * Removes specified customer from company.
   *
   * @param c Customer to be removed
   */
  public void removeUser(Customer c) {
    workingCompany.removeUser(c);
  }

  /**
   * Gives the shipment to specified transportation personal
   *
   * @param s  Shipment to be handed to transportation personal
   * @param tp Transportation personal to be handed to
   */
  public void handToTransportationP(Shipment s, TransportationPersonal tp) {
    tp.addShipment(s);
    s.setResponsibleTransportationPersonal(tp);
    s.setResponsibleBranchEmp(null);

    if (s.getCurrentBranch() == s.getDestinationBranch()) {
      s.setStatus(Shipment.ShipmentStatus.DISTRIBUTION);
    } else {
      s.setStatus(Shipment.ShipmentStatus.ON_THE_WAY);
    }

    s.setCurrentBranch(null);

  }

  /**
   * Returns shipment with matched tracking number.
   *
   * @param trackingNumber
   * @return {@link Shipment}
   */
  public Shipment getShipmentByTrackingNumber(int trackingNumber) {
    return workingCompany.getShipmentByTrackingNumber(trackingNumber);
  }


  /**
   * @return name, surname, working branch and username of branch employee
   */
  @Override
  public String toString() {
    return "Name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", working branch=" + workingBranch.getName() + '\'' +
            ", username=" + getUserName() + '\'';
  }

  /**
   * @return {@link Branch} which the branch employee works for
   */
  public Branch getWorkingBranch() {
    return workingBranch;
  }

}
