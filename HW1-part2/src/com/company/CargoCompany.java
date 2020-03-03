package com.company;

import java.util.ArrayList;

/**
 * Company who keeps track of its employees and customers
 *
 * @author Fatih Kaan Salgir
 */

public class CargoCompany {
  private ArrayList<Administrator> administrators;
  private ArrayList<Branch> branches;
  private ArrayList<TransportationPersonal> transportationPersonals;
  private ArrayList<Customer> users;

  public CargoCompany() {
    this.administrators = new ArrayList<>();
    this.branches = new ArrayList<>();
    this.transportationPersonals = new ArrayList<>();
    this.users = new ArrayList<>();
  }

  /**
   * Returns the shipment with specified tracking number
   *
   * @param trackingNumber
   * @return {@link Shipment}
   */
  public Shipment getShipmentByTrackingNumber(int trackingNumber) {

    for (TransportationPersonal tp : transportationPersonals) {
      for (Shipment s : tp.getShipments()) {
        if (s.getTrackingNumber() == trackingNumber) {
          return s;
        }
      }
    }

    for (Branch b : branches) {
      for (Shipment s : b.getShipments()) {
        if (s.getTrackingNumber() == trackingNumber) {
          return s;
        }
      }
    }
    return null;
  }

  /**
   * Prints the information about shipment with specified tracking number, if it exists.
   *
   * @param trackingNumber
   */
  public void trackShipment(int trackingNumber) {
    Shipment s = getShipmentByTrackingNumber(trackingNumber);
    if (s == null)
      System.out.println("No shipment with specified tracking number.");
    else
      s.shipmentInfo();
  }

  /**
   * Adds new admin to company
   *
   * @param admin
   */
  public void addAdmin(Administrator admin) {
    administrators.add(admin);
  }

  /**
   * @return number of administrators in the company
   */
  public int getNoAdmin() {
    return administrators.size();
  }

  /**
   * Adds new branch to company
   *
   * @param b {@link Branch} to be added to company
   */
  public void addBranch(Branch b) {
    branches.add(b);
  }

  public Branch getBranch(int i) {
    return branches.get(i);
  }

  /**
   * Removes specified branch from company
   *
   * @param b Branch to be removed
   */
  public void removeBranch(Branch b) {
    branches.remove(b);
  }

  /**
   * Deletes the transportation employee from company
   *
   * @param tp Transportation employee to be removed */
  public void removeTransportationPersonal(TransportationPersonal tp) {
    transportationPersonals.remove(tp);
  }

  /**
   * @return number of branches in the company
   */
  public int getNoBranches() {
    return branches.size();
  }

  /**
   * @return number of transportation personals in the company
   */
  public int getNoTransportationPersonals() {
    return transportationPersonals.size();
  }

  public TransportationPersonal getTransportationPersonal(int index) {
    return transportationPersonals.get(index);
  }

  /**
   * Adds new transportation personal to company
   *
   * @param tp Transportation personal to be added
   */
  public void addTransportationPersonal(TransportationPersonal tp) {
    transportationPersonals.add(tp);
  }

  /**
   * Deletes specified branch employee from company
   *
   * @param emp Branch employee to be removed
   */
  public void removeBranchEmp(BranchEmployee emp) {
    emp.getWorkingBranch().removeBranchEmp(emp);
  }

  /**
   * Adds new customer to company
   *
   * @param c Customer to be added
   */
  public void addUser(Customer c) {
    users.add(c);
  }

  /**
   * Deletes specified customer from company
   *
   * @param c Customer to be removed
   */
  public void removeUser(Customer c) {
    users.remove(c);
  }

  /**
   * Returns the worker matches the username.
   *
   * @param userName
   * @return {@link Worker}
   */
  public Worker getWorkerByUsername(String userName) {
    for (Administrator a : administrators) {
      if (a.getUserName().equals(userName)) {
        return a;
      }
    }
    for (TransportationPersonal tp : transportationPersonals) {
      if (tp.getUserName().equals(userName)) {
        return tp;
      }
    }
    for (Branch b : branches) {
      for (BranchEmployee be : b.getBranchEmps()) {
        if (be.getUserName().equals(userName)) {
          return be;
        }
      }
    }
    return null;
  }

  /**
   * Returns the branch matches with the inputted name
   *
   * @param name
   * @return {@link Branch}
   */
  public Branch getBranchByName(String name) {
    for (Branch b : branches) {
      if (b.getName().equals(name)) {
        return b;
      }
    }
    return null;
  }


  /**
   * Prints all the branches
   */
  public void listBranches() {
    int i = 1;
    for (Branch b : branches) {
      System.out.println(i + ") " + b);
      i++;
    }
  }

  /**
   * Prints all the branch employees categorized with their branches
   */
  public void listBranchesEmps() {
    int l = 1;
    for (Branch b : branches) {
      System.out.println("  " + b);
      for (BranchEmployee be : b.getBranchEmps()) {
        System.out.println(l + ") " + be);
        l++;
      }
    }
  }

  public BranchEmployee getBranchEmp(int index) {
    int l = 1;
    for (Branch b : branches) {
      for (BranchEmployee be : b.getBranchEmps()) {
        if (l == index) {
          return be;
        }
        l++;
      }
    }
    return null;
  }

  /**
   * prints all the transportation personals in the company
   */
  public void listTransportationPersonals() {
    int i = 1;
    for (TransportationPersonal tp : transportationPersonals) {
      System.out.println(i + ") " + tp);
    }
  }

  /**
   * Returns the customer matches with the specified name and surname
   *
   * @param name    Name to be inquired
   * @param surname Surname to be inquired
   * @return {@link Customer}
   */
  public Customer getUserByNameSurname(String name, String surname) {
    for (Customer c : users) {
      if (c.getName().equals(name) && c.getSurname().equals(surname)) {
        return c;
      }
    }
    return null;
  }
}
