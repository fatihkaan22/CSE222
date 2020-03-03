package com.company;

import java.util.Scanner;

public class Main {

  /**
   * First calls all the test methods and then calls the mainMenu method which is user interface of program.
   *
   * @author Fatih Kaan Salgir
   */
  public static void main(String[] args) {

    //TEST

    CargoCompany cargoSystem = new CargoCompany();
    System.out.println("\n--- ADDING ADMINISTRATORS ---");
    Administrator admin1 = new Administrator(cargoSystem, "admin1", "1234");
    cargoSystem.addAdmin(admin1);
    Administrator admin2 = new Administrator(cargoSystem, "admin2", "1234");
    cargoSystem.addAdmin(admin2);

    System.out.println("\n--- ADDING BRANCHES ---");
    Branch bandirmaSube = new Branch("Bandirma");
    Branch izmirSube = new Branch("Izmir");
    Branch korfezSube = new Branch("Korfez");
    Branch gebzeSube = new Branch("Gebze");
    admin1.addBranch(bandirmaSube);
    admin1.addBranch(izmirSube);
    admin1.addBranch(korfezSube);
    admin1.addBranch(gebzeSube);

    System.out.println("\n--- ADDING BRANCH EMPLOYEES ---");
    BranchEmployee bandirmaSubeCalisani = new BranchEmployee("Umit", "Yildiz", bandirmaSube, cargoSystem, "1234");
    BranchEmployee gebzeSubeCalisani = new BranchEmployee("Mehmet", "Demir", gebzeSube, cargoSystem, "1234");
    BranchEmployee korfezSubeCalisani = new BranchEmployee("Yusuf", "Isci", korfezSube, cargoSystem, "1234");
    BranchEmployee izmirSubeCalisani = new BranchEmployee("Burak", "Savas", izmirSube, cargoSystem, "1234");

    admin1.addBranchEmp(gebzeSubeCalisani, gebzeSube);
    admin1.addBranchEmp(korfezSubeCalisani, korfezSube);
    admin1.addBranchEmp(bandirmaSubeCalisani, bandirmaSube);

    System.out.println("\n--- ADDING TRANSPORTATION PERSONALS ---");
    TransportationPersonal gebzeKorfezAktarim = new TransportationPersonal(korfezSube, "name1", "surname1", "pass");
    TransportationPersonal korfezIzmirAktarim = new TransportationPersonal(izmirSube, "name2", "surname2", "pass");
    TransportationPersonal izmirDagiticisi = new TransportationPersonal(null, "name3", "surname3", "pass");

    admin1.addTransportationPersonal(gebzeKorfezAktarim);
    admin1.addTransportationPersonal(korfezIzmirAktarim);
    admin1.addTransportationPersonal(izmirDagiticisi);

    Sender s1 = new Sender("kaan", "salgir");
    Receiver r1 = new Receiver("berkay", "aslan", "Izmir Alsancak");
    Shipment s = new Shipment(s1, r1);

    System.out.println("\n--- DELIVERY PROCESS ---");
    gebzeSubeCalisani.acceptShipmentFromCustomer(s, izmirSube);
    gebzeSubeCalisani.handToTransportationP(s, gebzeKorfezAktarim);
    gebzeKorfezAktarim.handToBranch(s, korfezSube);
    s.shipmentInfo();
    System.out.println();
    System.out.println("\n--- ADDING INFORMATION ---");
    korfezSubeCalisani.addShipmentInformation(s.getTrackingNumber(), "Subeden ayrildi");
    System.out.println(s.getInfoMessage() + "\n");
    s.shipmentInfo();
    System.out.println();
    korfezSubeCalisani.handToTransportationP(s, korfezIzmirAktarim);
    s.shipmentInfo();
    System.out.println();
    korfezIzmirAktarim.handToBranch(s, izmirSube);
    s.shipmentInfo();
    System.out.println("\n--- ADDING INFORMATION ---");
    izmirSubeCalisani.addShipmentInformation(s.getTrackingNumber(), "Bugun dagitilacak");
    System.out.println(s.getInfoMessage());
    System.out.println();
    izmirSubeCalisani.handToTransportationP(s, izmirDagiticisi);
    s.shipmentInfo();
    System.out.println();
    izmirDagiticisi.changeStatusToDelivered(s);
    s.shipmentInfo();

    System.out.println("\n--- TRACK ---");
    cargoSystem.trackShipment(2);

    System.out.println("\n--- REMOVING BRANCHES AND EMPLOYEES ---");
    admin1.removeBranch(korfezSube);
    admin1.removeBranchEmp(korfezSubeCalisani);
    admin1.removeTransportationPersonal(gebzeKorfezAktarim);

    mainMenu(cargoSystem);

  }

  /**
   * Main menu of the program. It used to login the system with username and password,
   * or track shipment by entering tracking number.
   *
   * @param cargoSystem
   */
  public static void mainMenu(CargoCompany cargoSystem) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      final int LOGIN = 1;
      final int TRACK = 2;
      int choice = 0;
      System.out.println("  Cargo Automation System");
      System.out.println("1) Login");
      System.out.println("2) Track Shipment");

      while (choice != LOGIN && choice != TRACK) choice = scanner.nextInt();

      switch (choice) {
        case LOGIN:
          System.out.print("Username: ");
          String username = scanner.next();
          System.out.print("Password: ");
          String password = scanner.next();

          Worker login = cargoSystem.getWorkerByUsername(username);
          if (login == null) System.out.println("No such user.");
          else if (Worker.getEncryptedPassword(password).equals(login.getEncryptedPassword())) {
            System.out.println("Login successful");

            switch (login.getPosition()) {
              case ADMIN:
                adminMenu(cargoSystem, (Administrator) cargoSystem.getWorkerByUsername(username.strip()));
                break;
              case BRANCH_EMP:
                branchEmpMenu(cargoSystem, (BranchEmployee) cargoSystem.getWorkerByUsername(username.strip()));
                break;
              case TRANSPORTATION_PERSONAL:
                transportationPersonalMenu(cargoSystem, (TransportationPersonal) cargoSystem.getWorkerByUsername(username.strip()));
                break;
            }
          } else
            System.out.println("Invalid Password");
          break;
        case TRACK:
          System.out.print("Tracking Number: ");
          int trackingNumber = scanner.nextInt();
          cargoSystem.trackShipment(trackingNumber);
      }
    }
  }

  /**
   * {@link Administrator} menu to choose desired operation.
   *
   * @param cargoSystem
   * @param admin
   */
  private static void adminMenu(CargoCompany cargoSystem, Administrator admin) {
    Scanner s = new Scanner(System.in);
    int choice = -1;
    while (choice != 0) {
      choice = -1;
      System.out.println("  Admin: " + admin.getUserName());
      System.out.println("1) Add Branch");
      System.out.println("2) Remove Branch");
      System.out.println("3) Add Branch Employee");
      System.out.println("4) Remove Branch Employee");
      System.out.println("5) Add Transportation Personal");
      System.out.println("6) Remove Transportation Personal");
      System.out.println("0) Log out");

      while (choice > 6 || choice < 0) choice = s.nextInt();

      switch (choice) {
        case 1: //add branch
          System.out.print("Branch name: ");
          //TODO:check if exist
          admin.addBranch(new Branch(s.next()));
          break;
        case 2: //remove branch
          System.out.print("Enter branch name to remove or type 'l' to see all branches: ");
          String branchName = s.next();
          if (branchName.equals("l")) {
            cargoSystem.listBranches();
            branchName = s.next();
          }
          Branch b = cargoSystem.getBranchByName(branchName);
          if (b == null) System.out.println("Branch not found.");
          else {
            cargoSystem.removeBranch(b);
            System.out.println("Branch removed.");
          }
          break;
        case 3: //add branch emp
          System.out.println("Employee name: ");
          String name = s.next();
          System.out.println("Employee surname: ");
          String surname = s.next();
          System.out.println("Employee password: ");
          String password = s.next();
          System.out.println("In which branch this employee will work?");
          cargoSystem.listBranches();
          int branchIndex = s.nextInt() - 1;
          Branch branch = admin.getBranch(branchIndex);
          // TODO: check input
          BranchEmployee be = new BranchEmployee(name, surname, branch, cargoSystem, password);
          admin.getBranch(branchIndex).addBranchEmp(be);
          System.out.println("New branch employee created.");
          System.out.println(be);
          break;
        case 4: //remove branch emp
          //TODO:check if exist
          cargoSystem.listBranchesEmps();
          System.out.println("Choose which branch employee to remove.");
          BranchEmployee branchEmployee = cargoSystem.getBranchEmp(s.nextInt());
          cargoSystem.removeBranchEmp(branchEmployee);
          break;
        case 5: //add transportation personal
          System.out.println("Transportation personal name: ");
          name = s.next();
          System.out.println("Transportation personal surname: ");
          surname = s.next();
          System.out.println("Transportation personal password: ");
          password = s.next();
          System.out.println("Transportation personal destination branch name (enter 'l' to see all branches)");
          System.out.println("(enter working branch if its local distributor)");
          branchName = s.next();
          if (branchName.equals("l")) {
            cargoSystem.listBranches();
            branchName = s.next();
          }
          Branch destBranch = cargoSystem.getBranchByName(branchName);
          if (destBranch == null) System.out.println("Branch not found.");
          else cargoSystem.addTransportationPersonal(new TransportationPersonal(destBranch, name, surname, password));
          break;
        case 6:
          if (cargoSystem.getNoTransportationPersonals() != 0) {
            cargoSystem.listTransportationPersonals();
            System.out.println("Enter transportation personal number to remove");
            int tpIndex = s.nextInt() - 1;
            cargoSystem.removeTransportationPersonal(cargoSystem.getTransportationPersonal(tpIndex));
          } else {
            System.out.println("No transportation personal");
          }
          break;
      }
    }
  }


  /**
   * {@link TransportationPersonal} menu to choose desired operation.
   *
   * @param cargoSystem
   * @param tp          Transportation Personal who carry out operations.
   */
  private static void transportationPersonalMenu(CargoCompany cargoSystem, TransportationPersonal tp) {
    Scanner s = new Scanner(System.in);
    int choice = -1;
    while (choice != 0) {
      System.out.println("  Transportation Personal: " + tp.getUserName());
      System.out.println("1) Update shipment status to delivered");
      System.out.println("2) Hand shipment to branch employee");
      System.out.println("0) Log out");
      choice = s.nextInt();
      switch (choice) {
        case 1:
          if (tp.getNoShipment() != 0) {
            System.out.println("Enter tracking number to update status to delivered:  ");
            System.out.println("Enter 'l' to list all shipments you responsible for.");
            String trackingNumber = s.next();
            if (trackingNumber.equals("l")) {
              tp.listShipments();
              trackingNumber = s.next();
            }
            Shipment shipment = cargoSystem.getShipmentByTrackingNumber(Integer.parseInt(trackingNumber));
            tp.changeStatusToDelivered(shipment);
          } else
            System.out.println("No shipment");
          break;
        case 2:
          if (tp.getNoShipment() != 0) {
            System.out.println("Enter tracking number to hand shipment to branch employee:  ");
            System.out.println("(Enter 'l' to list all shipments you responsible for.)");
            String trackingNumber = s.next();
            if (trackingNumber.equals("l")) {
              tp.listShipments();
              trackingNumber = s.next();
            }
            Shipment shipment = cargoSystem.getShipmentByTrackingNumber(Integer.parseInt(trackingNumber));
            System.out.println("Enter branch name to hand the shipment:  ");
            //TODO:press l to list
            cargoSystem.listBranches();
            String branchName = s.next();
            Branch b = cargoSystem.getBranchByName(branchName);
            tp.handToBranch(shipment, b);
          } else
            System.out.println("No shipment");
          break;
      }

    }
  }

  /**
   * {@link BranchEmployee} menu to choose desired operation.
   *
   * @param cargoSystem
   * @param be          Branch Employee who carry out operations.
   */
  private static void branchEmpMenu(CargoCompany cargoSystem, BranchEmployee be) {
    Scanner s = new Scanner(System.in);
    int choice;
    do {
      System.out.println("  Branch Employee: " + be.getUserName());
      System.out.println("1) Accept shipment from customer");
      System.out.println("2) Add shipment information");
      System.out.println("3) Add customer");
      System.out.println("4) Remove customer");
      System.out.println("5) Edit cargo status");
      System.out.println("0) Log out");
      choice = getInput(0, 5);
      switch (choice) {
        case 1:
          System.out.println("Enter sender name: ");
          String senderName = s.next();
          System.out.println("Enter sender surname: ");
          String senderSurname = s.next();
          System.out.println("Enter receiver name: ");
          String receiverName = s.next();
          System.out.println("Enter receiver surname: ");
          String receiverSurname = s.next();
          System.out.println("Enter address:");
          s.nextLine();
          String address = s.nextLine();
          System.out.println("Enter destination branch, press 'l' to see all branches:");
          String branchName = s.next();
          Sender sender = new Sender(senderName, senderSurname);
          Receiver receiver = new Receiver(receiverName, receiverSurname, address);
          Shipment shipment = new Shipment(sender, receiver);
          if (branchName.equals("l")) {
            cargoSystem.listBranches();
            branchName = s.next();
            Branch branch = cargoSystem.getBranchByName(branchName);
            if (branch == null) System.out.println("Branch not found, assumed its empty");
            be.acceptShipmentFromCustomer(shipment, branch);
          } else if (branchName.equals(""))
            be.acceptShipmentFromCustomer(shipment);
          else {
            Branch branch = cargoSystem.getBranchByName(branchName);
            if (branch == null) System.out.println("Branch not found, assumed its empty");
            be.acceptShipmentFromCustomer(shipment, branch);
          }
          break;

        case 2:
          System.out.println("Enter tracking number to add info: ");
          int trackingNumber = s.nextInt();
          shipment = cargoSystem.getShipmentByTrackingNumber(trackingNumber);
          if (shipment == null) System.out.println("Not found");
          else {
            System.out.println("Enter info: ");
            s.nextLine();
            String info = s.nextLine();
            shipment.addInfoMessage(info, be.getUserName());
            System.out.println(shipment.getInfoMessage());
          }
          break;
        case 3:
          System.out.println("Enter customer name: ");
          String name = s.next();
          System.out.println("Enter customer surname: ");
          String surname = s.next();
          System.out.println("What is the position of customer (sender/receiver): ");
          String pos = s.next();
          if (pos.equals("sender"))
            be.addUser(new Sender(name, surname));
          else if (pos.equals("receiver")) {
            System.out.println("Enter address: ");
            address = s.nextLine();
            be.addUser(new Receiver(name, surname, address));
          }
          break;
        case 4:
          System.out.println("Enter customer name: ");
          name = s.next();
          System.out.println("Enter customer surname: ");
          surname = s.next();
          Customer c = cargoSystem.getUserByNameSurname(name, surname);
          if (c != null)
            be.removeUser(c);
          else
            System.out.println("Not found");
          break;
        case 5:
          System.out.println("Enter tracking number: ");
          trackingNumber = s.nextInt();
          shipment = cargoSystem.getShipmentByTrackingNumber(trackingNumber);
          if (shipment != null) {
            System.out.println("Choose status: ");
            int i = 1;
            for (Shipment.ShipmentStatus status : Shipment.ShipmentStatus.values()) {
              System.out.println(i + ") " + status);
              i++;
            }
            cargoSystem.getShipmentByTrackingNumber(trackingNumber).setStatus(getInput(1, 4));
          } else
            System.out.println("Not found");
          break;
      }

    } while (choice != 0);

  }

  /**
   * Gets an int from user between specified constrains.
   *
   * @param lowerBoundary minimum value to be entered
   * @param upperBoundary maximum value to be entered
   * @return
   */
  private static int getInput(int lowerBoundary, int upperBoundary) {
    Scanner s = new Scanner(System.in);
    int result;
    do {
      result = s.nextInt();
    } while (result < lowerBoundary || result > upperBoundary);
    return result;
  }

}

