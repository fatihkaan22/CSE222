package com.company;

/**
 * Company who keeps track of its employees and customers
 *
 * @author Fatih Kaan Salgir
 */

public class CargoCompany {
  private DynamicArray<Administrator> administrators;
  private DynamicArray<Branch> branches;
  private DynamicArray<TransportationPersonal> transportationPersonals;
  private DynamicArray<Customer> users;
  private int noBranches, noTransportationPersonals, noAdmin, noUsers;

  public CargoCompany() {
    this.administrators = new DynamicArray<>();
    this.branches = new DynamicArray<>();
    this.transportationPersonals = new DynamicArray<>();
    this.users = new DynamicArray<>();
    this.noAdmin = 0;
    this.noBranches = 0;
    this.noTransportationPersonals = 0;
    this.noUsers = 0;
  }

  /**
   * Returns the shipment with specified tracking number
   *
   * @param trackingNumber
   * @return {@link Shipment}
   */
  public Shipment getShipmentByTrackingNumber(int trackingNumber) {
    for (int i = 0; i < noAdmin; i++) {
      Administrator a = administrators.get(i);

      for (int j = 0; j < a.getNoTransportationPersonals(); j++) {
        TransportationPersonal tp = a.getTransportationPersonal(j);
        for (int k = tp.getNoShipment() - 1; k >= 0; k++) {
          Shipment s = tp.getShipment(k);
          if (s.getTrackingNumber() == trackingNumber) {
            return s;
          }
        }
      }

      for (int j = 0; j < a.getNoBranches(); j++) {
        Branch b = a.getBranch(j);
        for (int k = 0; k < b.getNoShipments(); k++) {
          Shipment s = b.getShipment(k);
          if (s.getTrackingNumber() == trackingNumber) {
            return s;
          }
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
    noAdmin++;
  }

  /**
   * @return number of administrators in the company
   */
  public int getNoAdmin() {
    return noAdmin;
  }

  /**
   * Adds new branch to company
   *
   * @param b {@link Branch} to be added to company
   */
  public void addBranch(Branch b) {
    branches.add(b);
    noBranches++;
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
    noBranches--;
  }

  /**
   * Deletes the transportation employee from company
   *
   * @param tp Transportation employee to be removed
   */
  public void removeTransportationPersonal(TransportationPersonal tp) {
    transportationPersonals.remove(tp);
    noTransportationPersonals--;
  }

  /**
   * @return number of branches in the company
   */
  public int getNoBranches() {
    return noBranches;
  }

  /**
   * @return number of transportation personals in the company
   */
  public int getNoTransportationPersonals() {
    return noTransportationPersonals;
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
    noTransportationPersonals++;
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
    noUsers++;
  }

  /**
   * Deletes specified customer from company
   *
   * @param c Customer to be removed
   */
  public void removeUser(Customer c) {
    users.remove(c);
    noUsers--;
  }

  /**
   * Returns the worker matches the username.
   *
   * @param userName
   * @return {@link Worker}
   */
  public Worker getWorkerByUsername(String userName) {
    for (int i = 0; i < noAdmin; i++) {
      if (administrators.get(i).getUserName().equals(userName)) {
        return administrators.get(i);
      }
    }
    for (int i = 0; i < noTransportationPersonals; i++) {
      if (transportationPersonals.get(i).getUserName().equals(userName)) {
        return transportationPersonals.get(i);
      }
    }
    for (int i = 0; i < noBranches; i++) {
      Branch b = getBranch(i);
      for (int j = 0; j < b.getNoBranchEmps(); j++) {
        if (b.getBranchEmp(j).getUserName().equals(userName)) {
          return b.getBranchEmp(j);
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
    for (int i = 0; i < noBranches; i++) {
      if (getBranch(i).getName().equals(name)) {
        return getBranch(i);
      }
    }
    return null;
  }


  /**
   * Prints all the branches
   */
  public void listBranches() {
    for (int i = 0; i < noBranches; i++) {
      System.out.println((i + 1) + ") " + getBranch(i));
    }
  }

  /**
   * Prints all the branch employees categorized with their branches
   */
  public void listBranchesEmps() {
    int l = 1;
    for (int i = 0; i < noBranches; i++) {
      Branch b = getBranch(i);
      System.out.println("  " + b);
      for (int j = 0; j < b.getNoBranchEmps(); j++) {
        System.out.println(l + ") " + b.getBranchEmp(j));
        l++;
      }
    }
  }

  public BranchEmployee getBranchEmp(int index) {
    int l = 1;
    for (int i = 0; i < noBranches; i++) {
      Branch b = getBranch(i);
      for (int j = 0; j < b.getNoBranchEmps(); j++) {
        if (l == index) {
          return b.getBranchEmp(j);
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
    for (int i = 0; i < noTransportationPersonals; i++) {
      System.out.println(i + 1 + ") " + getTransportationPersonal(i));
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
    for (int i = 0; i < noUsers; i++) {
      if (users.get(i).getName().equals(name) && users.get(i).getSurname().equals(surname)) {
        return users.get(i);
      }
    }
    return null;
  }
}

