package com.company;

/**
 * Branch which stores shipment information and has branch employees
 *
 * @author Fatih Kaan Salgir
 */

public class Branch {
  private String name;
  private int noBranchEmps;
  private int noShipments;
  private DynamicArray<BranchEmployee> branchEmps;
  private DynamicArray<Shipment> shipments;

  public Branch(String name) {
    this.name = name;
    this.branchEmps = new DynamicArray<>();
    this.shipments = new DynamicArray<>();
    this.noBranchEmps = 0;
    this.noShipments = 0;
  }

  /**
   * @return number of branch employees who works for the branch
   */
  public int getNoBranchEmps() {
    return noBranchEmps;
  }

  /**
   * @return number of shipments in the branch
   */
  public int getNoShipments() {
    return noShipments;
  }

  /**
   * Adds new branch employee to the branch.
   *
   * @param b Branch employee to be added
   */
  public void addBranchEmp(BranchEmployee b) {
    branchEmps.add(b);
    noBranchEmps++;
  }

  /**
   * Adds new shipment to the branch
   *
   * @param s Shipment to be added
   */
  public void addShipment(Shipment s) {
    shipments.add(s);
    noShipments++;
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
    noBranchEmps--;
  }

  /**
   * @return name and number of branch employees of the branch
   */
  @Override
  public String toString() {
    return "Branch{" +
            "name='" + name + '\'' +
            ", noBranchEmps=" + noBranchEmps +
            '}';
  }
}
