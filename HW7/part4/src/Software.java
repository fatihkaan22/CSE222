import java.util.Objects;

public class Software {
  String name;
  Integer quantity;
  Double price;

  public Software(String name, Integer quantity, Double price) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  @Override
  public String toString() {
    return "{" +
            "name='" + name + '\'' +
            ", quantity=" + quantity +
            ", price=" + price +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Software)) return false;
    Software software = (Software) o;
    return (software.name == null || name.equals(software.name))
            && (software.price == null || price.equals(software.price))
            && (software.quantity == null || quantity.equals(software.quantity));
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, quantity, price);
  }
}
