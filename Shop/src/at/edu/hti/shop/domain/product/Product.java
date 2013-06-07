
package at.edu.hti.shop.domain.product;

import at.edu.hti.shop.domain.category.ICategory;

public class Product implements Comparable<Product>, IProduct {
  private ICategory category = null;
  private long id;
  private int leadTime = 0;
  private final String name;
  private double prize;
  private double weight;

  public Product(long id, String name, double prize, double weight) {
    if (name == null) {
      throw new NullPointerException("'name' must not be null");
    }
    if (name.trim().length() == 0) {
      throw new IllegalArgumentException("'name' must not be empty");
    }
    if (prize <= 0) {
      throw new IllegalArgumentException("'prize' must not be less or equal than 0");
    }
    if (weight <= 0) {
      throw new IllegalArgumentException("'weight' must not be less or equal than 0");
    }
    this.name = name;
    this.id = id;
    this.prize = prize;
    this.weight = weight;
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
    IProduct p = (IProduct) obj;
    if (!p.getName().equals(name)) {
      return false;
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public ICategory getCategory() {
    return category;
  }

  @Override
  public long getId() {
    return id;
  }

  /** {@inheritDoc} */
  @Override
  public int getLeadTime() {
    return leadTime;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getPrize() {
    return prize;
  }

  /** {@inheritDoc} */
  @Override
  public double getWeight() {
    return weight;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    int result = 31;
    result = result + name.hashCode();
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public IProduct setCategory(ICategory category) {
    if (category == null) {
      throw new NullPointerException("'category' must not be null");
    }
    this.category = category;
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IProduct setLeadTime(int leadTime) {
    if (leadTime < 0) {
      throw new IllegalArgumentException("Lead Time must not be less than 0");
    }
    this.leadTime = leadTime;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("<Product name=\"" + name + "\" id=\"" + id + "\" price=\"" + prize + "\" leadTime=\"" + leadTime + "\" weight=\"" + weight + "\" categoty=\""
      + category.getName() + "\"/>");
    return s.toString();
  }
}
