
package at.edu.hti.shop.domain.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import at.edu.hti.shop.domain.pricing.PriceStrategyFactory;

public class Order {

  private List<OrderLine> lines = new ArrayList<OrderLine>();
  private final UUID id;

  public Order() {
    this.id = UUID.randomUUID();
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Order)) {
      return false;
    }
    Order o = (Order) obj;
    if (!o.id.equals(this.id)) {
      return false;
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    int result = 31;
    result = 31 * result + id.hashCode();
    return result;
  }

  public boolean add(OrderLine l) {
    if (l == null) {
      throw new NullPointerException("'l' must not be null");
    }
    synchronized (lines) {
      if (lines.contains(l)) {
        throw new IllegalArgumentException("Order line [" + l + "] already contained in Order [" + this + "]");
      }
      l.attachToOrder(this);
      return lines.add(l);
    }
  }

  public boolean remove(OrderLine l) {
    if (l == null) {
      throw new NullPointerException("'e' must not be null");
    }
    synchronized (lines) {
      boolean removed = lines.remove(l);
      if (!removed) {
        throw new IllegalArgumentException("Order Line [" + l + "] not found within order [" + this + "]");
      }
    }
    return true;
  }

  public double getPrice() {
    return PriceStrategyFactory.getStrategy(PriceStrategyFactory.DEFAULT_PRICING_STRATEGY).calculate(Collections.unmodifiableList(lines));
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("<order id=\"" + id.toString() + "\" numberOfOrderLines=\"" + lines.size() + "\" rice=\"" + getPrice() + "\">");
    for (OrderLine l : lines) {
      s.append(l);
    }
    s.append("</order>");
    return s.toString();
  }

  public List<OrderLine> getOrderLines() {
    return Collections.unmodifiableList(lines);
  }

}
