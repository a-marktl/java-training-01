
package at.edu.hti.shop.domain.delivery;

import java.util.ArrayList;
import java.util.List;

public class Delivery implements IDelivery {

  private List<IPartialDelivery> parts = new ArrayList<IPartialDelivery>();

  /** {@inheritDoc} */
  @Override
  public IDelivery addPartialDelivery(IPartialDelivery part) {
    if (part == null) {
      throw new NullPointerException("'tu' must not be null");
    }
    synchronized (parts) {
      if (parts.contains(part)) {
        throw new IllegalArgumentException("Partial Delivery [" + part + "] already added to Delivery");
      }
      parts.add(part);
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IDelivery removePartialDelivery(IPartialDelivery part) {
    if (part == null) {
      throw new NullPointerException("'tu' must not be null");
    }
    synchronized (parts) {
      boolean removed = parts.remove(part);
      if (!removed) {
        throw new IllegalArgumentException("Partial Delivery [" + part + "] not found in Delivery");
      }
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public double getPrice() {
    double price = 0;
    for (IPartialDelivery part : parts) {
      price = price + part.getPrice();
    }
    return price;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("<Delivery price=\"" + getPrice() + "\" weight=\"" + getWeight() + "\">");
    for (IPartialDelivery part : parts) {
      s.append(part);
    }
    s.append("</Delivery>");
    return s.toString();
  }

  /** {@inheritDoc} */
  @Override
  public double getWeight() {
    double weight = 0;
    for (IPartialDelivery part : parts) {
      weight = weight + part.getWeight();
    }
    return weight;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
