public class SoftwareNode<E extends Comparable<E>> implements Comparable<SoftwareNode<E>> {
  E key;
  KeyType searchCriteria;
  Software software;

  enum KeyType {
    NAME,
    QUANTITY,
    PRICE
  }

  @Override
  public int compareTo(SoftwareNode<E> softwareNode) {
    if (softwareNode.searchCriteria != this.searchCriteria) {
      return softwareNode.searchCriteria.compareTo(this.searchCriteria);
    } else if (softwareNode.key.equals(this.key) && softwareNode.software.equals(this.software)) {
      return 0;
    } else if (softwareNode.key.equals(this.key) && !softwareNode.software.equals(this.software)) {
      return 1; // to allow duplicate keys
    }
    return softwareNode.key.compareTo(key);
  }


  @Override
  public boolean equals(Object obj) {
    SoftwareNode t = (SoftwareNode) obj;
    return this.software.name.equals(t.software.name);
  }

  public SoftwareNode(E key, KeyType searchCriteria, Software software) {
    this.key = key;
    this.searchCriteria = searchCriteria;
    this.software = software;
  }

  /**
   * Returns the information of a node of the tree, can be used for debugging purposes
   *
   * @return a node of tree
   */
  @Override
  public String toString() {
    return "{" +
            "key=" + key +
            ", searchCriteria=" + searchCriteria +
            ", software=" + software +
            '}';
  }
}
