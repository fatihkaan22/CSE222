package com.company;

/**
 * Administrator of cargo company who have authority to add or remove new employees and branches.
 *
 * @author Fatih Kaan Salgir
 */

public class Administrator extends Worker {

  /**
   * Cargo company to be managed
   */

  private CargoCompany company;

  /**
   * @param company  company to be managed
   * @param username username of administrator
   * @param password password of administrator
   */
  public Administrator(CargoCompany company, String username, String password) {
    super(username, password, Position.ADMIN);
    this.company = company;
  }

  /**
   * Adds new branch to cargo company
   *
   * @param b New branch to be added
   */
  public void addBranch(Branch b) {
    company.addBranch(b);
  }

  public Branch getBranch(int i) {
    return company.getBranch(i);
  }

  /**
   * Removes specified branch from cargo company
   *
   * @param b Branch to be removed
   */
  public void removeBranch(Branch b) {
    company.removeBranch(b);
  }

  /**
   * @return number of branches
   */
  public int getNoBranches() {
    return company.getNoBranches();
  }

  /**
   * @return number of transportation personals
   */
  public int getNoTransportationPersonals() {
    return company.getNoTransportationPersonals();
  }

  public TransportationPersonal getTransportationPersonal(int index) {
    return company.getTransportationPersonal(index);
  }

  /**
   * Adds the branch employee to specified branch.
   *
   * @param emp    Branch employee to be added
   * @param branch Branch employee will be added this branch
   */
  public void addBranchEmp(BranchEmployee emp, Branch branch) {
    branch.addBranchEmp(emp);
  }

  /**
   * Removes specified branch employee from branch
   *
   * @param emp Branch employee to be removed
   */
  public void removeBranchEmp(BranchEmployee emp) {
    company.removeBranchEmp(emp);
  }

  /**
   * Adds new transportation personal to cargo company.
   *
   * @param tp Transportation personal to be added
   */
  public void addTransportationPersonal(TransportationPersonal tp) {
    company.addTransportationPersonal(tp);
  }

  /**
   * Removes transportation personal from cargo company.
   *
   * @param tp Transportation personal to be removed.
   */
  public void removeTransportationPersonal(TransportationPersonal tp) {
    company.removeTransportationPersonal(tp);
  }

}
