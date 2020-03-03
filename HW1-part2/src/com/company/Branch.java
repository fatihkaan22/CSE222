package com.company;

import java.util.ArrayList;

/**
 * Branch which stores shipment information and has branch employees
 *
 * @author Fatih Kaan Salgir
 */

public class Branch {
  private String name;
  private ArrayList<BranchEmployee> branchEmps;
  private ArrayList<Shipment> shipments;

  public Branch(String name) {
    this.name = name;
    this.branchEmps = new ArrayList<>();
    this.shipments = new ArrayList<>();
  }

  public ArrayList<BranchEmployee> getBranchEmps() {
    return branchEmps;
  }

  /**
   * @return number of branch employees who works for the branch
   */
  public int getNoBranchEmps() {
    return branchEmps.size();
  }

  /**
   * @return number of shipments in the branch
   */
  public int getNoShipments() {
    return shipments.size();
  }

  /**
   * Adds new branch employee to the branch.
   *
   * @param b Branch employee to be added
   */
  public void addBranchEmp(BranchEmployee b) {
    branchEmps.add(b);
  }

  /**
   * Adds new shipment to the branch
   *
   * @param s Shipment to be added
   */
  public void addShipment(Shipment s) {
    shipments.add(s);
  }

  /**
   * Gets the specified branch employee
   *
   * @param index
   * @return {@link BranchEmployee}
   */
  public BranchEmployee getBranchEmp(int index) {
    return branchEmps.get(index);
  }

  /**
   * Gets the specified shipment
   *
   * @param index
   * @return {@link Shipment}
   */
  public Shipment getShipment(int index) {
    return shipments.get(index);
  }

  /**
   * @return name of the branch
   */
  public String getName() {
    return name;
  }

  /**
   * Removes specified branch employee from the branch.
   *
   * @param emp branch employee to be removed from branch.
   */
  public void removeBranchEmp(BranchEmployee emp) {
    branchEmps.remove(emp);
  }

  /**
   * @return name and number of branch employees of the branch
   */
  @Override
  public String toString() {
    return "Branch{" +
            "name='" + name + '\'' +
            ", noBranchEmps=" + getNoBranchEmps() +
            '}';
  }

  public ArrayList<Shipment> getShipments() {
    return shipments;
  }
}
