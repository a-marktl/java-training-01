
package at.edu.hti.shop.domain.product;

import at.edu.hti.shop.domain.attribute.AttributeDefinition;
import at.edu.hti.shop.domain.category.Category;

import java.util.Map;

public class Product implements Comparable<Product>, IProduct {
  private String name;
  private long id;
  private double prize;

  public Product(long id, String name, double prize) {
    super();
    this.name = name;
    this.id = id;
    this.prize = prize;
  }

  public String getName() {
    return name;
  }

  public long getId() {
    return id;
  }

  public double getPrize() {
    return prize;
  }

  @Override
  public String toString() {
    return "Product [" + name + ", " + id + ", " + prize + "]";
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(Product o) {
    if (o == null) {
      throw new NullPointerException("'o' must not be null");
    }
    return o.getName().compareTo(name);
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      throw new NullPointerException("'obj' must not be null");
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Product)) {
      return false;
    }

    Product p = (Product) obj;

    if (!p.getName().equals(name)) {
      return false;
    }
    
    return true;

  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    int result = 31;
    result = result + name.hashCode();
    return result;
  }

    @Override
    public Map<AttributeDefinition, Object> getAttributeValues() {
        // @TODO
        //return
    }

    @Override
    public void setAttribute(AttributeDefinition attributeDefinition, Object value) {
        // @TODO
    }

    @Override
    public Category getCategory() {
        // @TODO
        //return
    }
}
