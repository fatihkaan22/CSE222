package com.company;

import java.util.Date;

/**
 * @author Fatih Kaan Salgir
 */

public class Shipment {

  /**
   * All possible situations for shipment
   */
  public enum ShipmentStatus {
    IN_THE_BRANCH,
    ON_THE_WAY,
    DISTRIBUTION {
      @Override
      public String toString() {
        return "Distribution"; //TODO: edit text
      }
    },
    DELIVERED {
      @Override
      public String toString() {
        return "Delivered";
      }
    }
  }

  private ShipmentStatus status;
  private final Date acceptanceDate;
  private static Integer trackingNoCounter = 1; //TODO: auto increase, unsigned
  private Integer trackingNumber; //TODO: auto increase, unsigned
  private Branch currentBranch; //TODO: none? (yolda)
  private Branch destinationBranch;
  private Sender sender;
  private Receiver receiver;
  private TransportationPersonal responsibleTransportationPersonal;
  private BranchEmployee responsibleBranchEmp;
  private String infoMessage;

  public Shipment(Sender sender, Receiver receiver) {
    this.sender = sender;
    this.receiver = receiver;
    this.status = ShipmentStatus.IN_THE_BRANCH;
    this.trackingNumber = ++trackingNoCounter;
    this.acceptanceDate = new Date();
    sender.setTrackingNumber(this.trackingNumber);
  }

  /**
   * @return Acceptance {@link Date} of the shipment
   */
  public Date getAcceptanceDate() {
    return acceptanceDate;
  }

  /**
   * Prints sender, receiver and status of shipment
   */
  public void shipmentInfo() {
    System.out.println("Sender: " + sender);
    System.out.println("Receiver: " + receiver);
    System.out.print("Status: ");
    switch (status) {
      case IN_THE_BRANCH:
        System.out.println("In the branch '" + currentBranch.getName() + '\'');
        break;
      case ON_THE_WAY:
        System.out.println("On the way to '" + responsibleTransportationPersonal.getDestinationBranch().getName() + '\'');
        break;
      default:
        System.out.println(status);
        break;
    }

  }

  public TransportationPersonal getTransportationPersonal() {
    return responsibleTransportationPersonal;
  }

  public BranchEmployee getResponsibleBranchEmp() {
    return responsibleBranchEmp;
  }

  public Integer getTrackingNumber() {
    return trackingNumber;
  }

  public void setCurrentBranch(Branch currentBranch) {
    this.currentBranch = currentBranch;
    if (currentBranch != null)
      this.setStatus(ShipmentStatus.IN_THE_BRANCH);
  }

  public Branch getCurrentBranch() {
    return currentBranch;
  }

  public Branch getDestinationBranch() {
    return destinationBranch;
  }

  public void setStatus(ShipmentStatus status) {
    this.status = status;
  }

  public void setResponsibleTransportationPersonal(TransportationPersonal responsibleTransportationPersonal) {
    this.responsibleTransportationPersonal = responsibleTransportationPersonal;
  }

  public void setResponsibleBranchEmp(BranchEmployee responsibleBranchEmp) {
    this.responsibleBranchEmp = responsibleBranchEmp;
  }

  public void setDestinationBranch(Branch destinationBranch) {
    this.destinationBranch = destinationBranch;
  }

  /**
   * Adds new custom information message to shipment
   *
   * @param infoMessage message to be added
   * @param editor      worker who adds the message
   */
  public void addInfoMessage(String infoMessage, String editor) {
    if (this.infoMessage != null)
      this.infoMessage = this.infoMessage + '\n' + editor + ": " + infoMessage;
    else
      this.infoMessage = editor + ": " + infoMessage;
  }

  public String getInfoMessage() {
    return infoMessage;
  }

  public ShipmentStatus getStatus() {
    return status;
  }

  /**
   * Returns the string with status, acceptance date, tracking number, sender, receiver and information message
   *
   * @return
   */
  @Override
  public String toString() {
    return "Shipment{" +
            "status=" + status +
            ", acceptanceDate=" + acceptanceDate +
            ", trackingNumber=" + trackingNumber +
            ", sender=" + sender +
            ", receiver=" + receiver +
            ", infoMessage='" + infoMessage + '\'' +
            '}';
  }

  /**
   * @param i status index + 1 to be updated
   */
  public void setStatus(int i) {
    if (i == 1) {
      setStatus(ShipmentStatus.IN_THE_BRANCH);
    } else if (i == 2) {
      setStatus(ShipmentStatus.ON_THE_WAY);
    } else if (i == 3) {
      setStatus(ShipmentStatus.DISTRIBUTION);
    } else if (i == 4) {
      setStatus(ShipmentStatus.DELIVERED);
    }

  }
}
