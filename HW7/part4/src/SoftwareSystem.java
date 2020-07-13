import java.util.ArrayList;

public class SoftwareSystem {

  String password;
  ArrayList<String> admins;
  SearchTree softwareTree;
  int noSoftware;

  public SoftwareSystem(String password) {
    this.password = password;
    this.softwareTree = new BinarySearchTree<>(); // it could be any tree implements SearchTree
    this.admins = new ArrayList<>();
    this.noSoftware = 0;
  }


  /**
   * Adds specified id to system
   *
   * @param id id of the admin
   */
  public void addAdmin(String id) {
    admins.add(id);
  }


  /**
   * Adds new software to system. Creates new nodes for each entitiy of the software
   *
   * @param software software to be added
   * @return true if successfully added, false otherwise
   */
  public boolean addSoftware(Software software) {
    noSoftware++;
    return softwareTree.add(new SoftwareNode<>(software.name, SoftwareNode.KeyType.NAME, software))
            && softwareTree.add(new SoftwareNode<>(software.quantity, SoftwareNode.KeyType.QUANTITY, software))
            && softwareTree.add(new SoftwareNode<>(software.price, SoftwareNode.KeyType.PRICE, software));
  }

  /**
   * Searches software by name
   *
   * @param searchCriteria the entity of software to search
   * @param name           name of the software
   * @return found software, null if doesn't exist
   * @throws IllegalArgumentException if specified search criteria doesn't match
   */
  public Software searchSoftware(SoftwareNode.KeyType searchCriteria, String name) {
    if (searchCriteria != SoftwareNode.KeyType.NAME) throw new IllegalArgumentException();
    Software software = new Software(name, null, null);
    SoftwareNode sn = (SoftwareNode) softwareTree.find(new SoftwareNode(name, searchCriteria, software));
    if (sn == null) return null;
    return sn.software;
  }

  /**
   * Searches software by quantity
   *
   * @param searchCriteria the entity of software to search
   * @param quantity       quantity of the software
   * @return found software, null if doesn't exist
   * @throws IllegalArgumentException if specified search criteria doesn't match
   */
  public Software searchSoftware(SoftwareNode.KeyType searchCriteria, Integer quantity) {
    if (searchCriteria != SoftwareNode.KeyType.QUANTITY) throw new IllegalArgumentException();
    Software software = new Software(null, quantity, null);
    SoftwareNode sn = (SoftwareNode) softwareTree.find(new SoftwareNode(quantity, searchCriteria, software));
    if (sn == null) return null;
    return sn.software;
  }

  /**
   * Searches software by price
   *
   * @param searchCriteria the entity of software to search
   * @param price          price of the software
   * @return found software, null if doesn't exist
   * @throws IllegalArgumentException if specified search criteria doesn't match
   */
  public Software searchSoftware(SoftwareNode.KeyType searchCriteria, Double price) {
    if (searchCriteria != SoftwareNode.KeyType.PRICE) throw new IllegalArgumentException();
    Software software = new Software(null, null, price);
    SoftwareNode sn = (SoftwareNode) softwareTree.find(new SoftwareNode(price, searchCriteria, software));
    if (sn == null) return null;
    return sn.software;
  }


  /**
   * Deletes all of the specified software from system.
   * If specified parameter is not in the system, it throws exception.
   *
   * @param software software to be deleted
   * @return deleted software
   */
  public boolean deleteSoftware(Software software) {
    noSoftware--;
    SoftwareNode snName = new SoftwareNode(software.name, SoftwareNode.KeyType.NAME, software);
    SoftwareNode snPrice = new SoftwareNode(software.price, SoftwareNode.KeyType.PRICE, software);
    SoftwareNode snQuantity = new SoftwareNode(software.quantity, SoftwareNode.KeyType.QUANTITY, software);
    if (snName.software == snQuantity.software && snName.software == snPrice.software) {
      softwareTree.remove(snName);
      softwareTree.remove(snPrice);
      softwareTree.remove(snQuantity);
      return true;
    } else
      return false;
  }

  /**
   * Decrease quantity by 1
   *
   * @param softwareName name of the software
   * @return null if software not found; if successfully removed returns software object
   */
  public Software soldSoftware(String softwareName) {
    //find
    Software s = searchSoftware(SoftwareNode.KeyType.NAME, softwareName);
    if (s == null) return null;
    // decrease quantity by 1 and reorder tree
    //if quantity is 0, then remove from tree
    deleteSoftware(s);
    s.quantity -= 1;
    if (s.quantity != 0)
      addSoftware(s);
    return s;
  }

  /**
   * @param old     software to be updated
   * @param updated software to be updated to
   * @return true if update happens successfully
   */
  public boolean updateSoftwareInfo(Software old, Software updated) {
    return deleteSoftware(old) && addSoftware(updated);
  }


  /**
   * Grabs only software list from whole tree
   *
   * @return software list
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    String str = softwareTree.toString();
    String[] softwareList = str.split("\n");

    for (int i = 0; i < noSoftware; i++) {
      sb.append(i + 1 + " - ");
      sb.append(softwareList[i], softwareList[i].indexOf("software"), softwareList[i].length() - 1);
      sb.append("\n");
    }

    return sb.toString();
  }
}
