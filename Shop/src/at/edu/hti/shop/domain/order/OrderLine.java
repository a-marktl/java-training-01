
package at.edu.hti.shop.domain.order;

import at.edu.hti.shop.domain.product.IProduct;

public class OrderLine implements Comparable<OrderLine> {
  private IProduct product;
  private int amount;
  private Order order;

  public OrderLine(IProduct product, int amount) {
    if (product == null) {
      throw new NullPointerException("'product' must not be null");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("'amount' must not be less or equal to 0");
    }
    this.product = product;
    this.amount = amount;
  }

  public OrderLine attachToOrder(Order o) {
    if (o == null) {
      throw new NullPointerException("'o' must not be null");
    }
    synchronized (this) {
      if (this.order != null) {
        throw new IllegalStateException("Order Line  [" + this + "] already attached to order [" + this.order + "]");
      }
      this.order = o;
    }
    return this;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("'amount' must not be less than 0");
    }
    if (amount == 0) {
      order.remove(this);
    }
    this.amount = amount;
  }

  public IProduct getProduct() {
    return product;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("<OrderLine amount=\"" + amount + "\" price=\"" + getPrice() + "\" weight=\"" + getWeight() + "\">");
    s.append(product);
    s.append("</OrderLine>");
    return s.toString();
  }

  public double getPrice() {
    return amount * product.getPrize();
  }

  public double getWeight() {
    return amount * product.getWeight();
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(OrderLine o) {
    if (o == null) {
      throw new NullPointerException("'o' must not be null");
    }
    return getProduct().getName().compareTo(o.getProduct().getName());
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof OrderLine)) {
      return false;
    }

    OrderLine ol = (OrderLine) obj;
    if (!ol.order.equals(order)) {
      return false;
    }

    if (!ol.getProduct().equals(product)) {
      return false;
    }

    if (ol.getAmount() != amount) {
      return false;
    }

    return true;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    int result = 31;
    result = 31 * result + order.hashCode();
    result = 31 * result + product.getName().hashCode();
    result = 31 * result + amount;
    return result;
  }
}
