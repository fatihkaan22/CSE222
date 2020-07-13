import java.util.Scanner;

public class Main {
  Scanner s = new Scanner(System.in);
  SoftwareSystem sys;

  public static void main(String[] args) {
    Main main = new Main();

    main.sys = new SoftwareSystem("pass");
    main.sys.addSoftware(new Software("Adobe Photoshop 6.0", 3, 200.0));
    main.sys.addSoftware(new Software("Adobe Photoshop 6.2", 2, 300.0));
    main.sys.addSoftware(new Software("Norton 4.5", 1, 100.0));
    main.sys.addSoftware(new Software("Norton 5.5", 3, 150.0));
    main.sys.addSoftware(new Software("Adobe Flash 3.3", 7, 80.0));
    main.sys.addSoftware(new Software("Adobe Flash 4.0", 3, 90.0));

    main.sys.addAdmin("123");

//    System.out.println("T1");
//    System.out.println(main.sys.softwareTree);
//
//    System.out.println("T2");
//    System.out.println(main.sys.searchSoftware(SoftwareNode.KeyType.NAME, "Adobe Photoshop 6.0"));
//    System.out.println("T3");
//    System.out.println(main.sys.searchSoftware(SoftwareNode.KeyType.QUANTITY, 1));
//    System.out.println("T4");
//    System.out.println(main.sys.searchSoftware(SoftwareNode.KeyType.PRICE, 90.0));
//    System.out.println("T5");
//    Software software = main.sys.searchSoftware(SoftwareNode.KeyType.PRICE, 90.0);
//    System.out.println(main.sys.soldSoftware(software.name));
//    System.out.println(main.sys.softwareTree); System.out.println("T6");
//    System.out.println(main.sys.deleteSoftware(software));
//    System.out.println(main.sys.softwareTree);
//    System.out.println("T7");
//    main.sys.updateSoftwareInfo(software, new Software("updated", 12, 1.23));
//    System.out.println(main.sys.softwareTree);

    boolean exit;
    do {
      exit = main.mainMenu();
    } while (!exit);


  }

  private boolean mainMenu() {
    boolean exit;
    System.out.println("0 - Exit");
    System.out.println("1 - Guest");
    System.out.println("2 - Admin");
    switch (s.nextInt()) {
      case 0:
        return true;
      case 1:
        do {
          exit = guestMenu();
        } while (!exit);
        return true;
      case 2:
        login();
        break;
    }
    return false;
  }

  private boolean guestMenu() {
    System.out.println("0 - Exit");
    System.out.println("1 - Search software by name");
    System.out.println("2 - Search software by quantity");
    System.out.println("3 - Search software by price");
    switch (s.nextInt()) {
      case 0:
        return true;
      case 1:
        s.nextLine();
        System.out.print("Name: ");
        System.out.println(sys.searchSoftware(SoftwareNode.KeyType.NAME, s.nextLine().replace("\n", "")));
        break;
      case 2:
        System.out.print("Quantity: ");
        System.out.println(sys.searchSoftware(SoftwareNode.KeyType.QUANTITY, s.nextInt()));
        break;
      case 3:
        System.out.print("Price: ");
        System.out.println(sys.searchSoftware(SoftwareNode.KeyType.PRICE, s.nextDouble()));
        break;
    }
    return false;
  }

  public void login() {
    String password;
    Scanner s = new Scanner(System.in);

    System.out.print("id: ");
    String id = s.nextLine();
    System.out.print("password: ");
    password = s.nextLine();

    if (sys.admins.contains(id) && sys.password.equals(password)) {
      boolean exit;
      do {
        exit = adminMenu(sys);
      } while (!exit);
    }
  }

  private boolean adminMenu(SoftwareSystem sys) {
    System.out.println("0 - Exit");
    System.out.println("1 - Search software by name");
    System.out.println("2 - Search software by quantity");
    System.out.println("3 - Search software by price");
    System.out.println("4 - Add software");
    System.out.println("5 - Remove software");
    System.out.println("6 - Update software info");
    System.out.println("7 - List all software");
    System.out.println("8 - Sold software");
    Software software;
    switch (s.nextInt()) {
      case 0:
        return true;
      case 1:
        s.nextLine();
        System.out.print("Name: ");
        System.out.println(sys.searchSoftware(SoftwareNode.KeyType.NAME, s.nextLine().replace("\n", "")));
        break;
      case 2:
        System.out.print("Quantity: ");
        System.out.println(sys.searchSoftware(SoftwareNode.KeyType.QUANTITY, s.nextInt()));
        break;
      case 3:
        System.out.print("Price: ");
        System.out.println(sys.searchSoftware(SoftwareNode.KeyType.PRICE, s.nextDouble()));
        break;
      case 4:
        System.out.println("Enter name, quantity and price");
        s.nextLine();
        sys.addSoftware(new Software(s.nextLine().replace("\n", ""),
                s.nextInt(), s.nextDouble()));
      case 5:
        System.out.println(sys);
        System.out.println("Which one do you want to remove? Enter name of software: ");
        s.nextLine();
        software = sys.searchSoftware(SoftwareNode.KeyType.NAME, s.nextLine().replace("\n", ""));
        if (software != null && sys.deleteSoftware(software))
          System.out.println("Removed");
        else
          System.out.println("Not found");
        break;
      case 6:
        System.out.println(sys);
        System.out.println("Enter name of the software to update: ");
        s.nextLine();
        software = sys.searchSoftware(SoftwareNode.KeyType.NAME, s.nextLine().replace("\n", ""));
        if (software == null) System.out.println("Not found");
        else {
          System.out.println("Enter new name: ");
          String name = s.nextLine().replace("\n", "");
          System.out.println("Enter new quantity: ");
          int quantity = s.nextInt();
          System.out.println("Enter new price: ");
          Double price = s.nextDouble();
          Software newSoftware = new Software(name, quantity, price);
          sys.updateSoftwareInfo(software, newSoftware);
        }
        break;
      case 7:
        System.out.println(sys);
        break;
      case 8:
        System.out.println(sys);
        System.out.println("Enter name of the software to sold: ");
        s.nextLine();
        if (sys.soldSoftware(s.nextLine().replace("\n", "")) == null) {
          System.out.println("Sold");
        } else
          System.out.println("Not found");
    }
    return false;
  }

}
