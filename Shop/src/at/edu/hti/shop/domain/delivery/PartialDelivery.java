
package at.edu.hti.shop.domain.delivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.edu.hti.shop.domain.order.OrderLine;
import at.edu.hti.shop.domain.pricing.PriceStrategyFactory;
import at.edu.hti.shop.domain.specification.ISpecification;

public class PartialDelivery implements IPartialDelivery {

  private List<OrderLine> lines = new ArrayList<>();
  private ISpecification<PartialDeliveryCandidate> specification = null;
  private String pricing = PriceStrategyFactory.DEFAULT_PRICING_STRATEGY;

  /** {@inheritDoc} */
  @Override
  public IPartialDelivery addOrderLine(OrderLine ol) throws DisapprovedOrderLineException {
    if (ol == null) {
      throw new NullPointerException("'ol' must not be null");
    }
    synchronized (lines) {
      if (specification != null && !specification.isSatisfiedBy(new PartialDeliveryCandidate(this, ol))) {
        //TODO add more detail about the exception
        throw new DisapprovedOrderLineException("Specification for Partial Delivery [" + this + "] and Order Line [" + ol + "] not met");
      }
    }
    if (lines.contains(ol)) {
      throw new IllegalArgumentException("Order Line [" + ol + "] already contained in Transport Unit [" + this + "]");
    }
    this.lines.add(ol);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public double getPrice() {
    return PriceStrategyFactory.getStrategy(pricing).calculate(Collections.unmodifiableList(lines));
  }

  public double getPrice(String pricing) {
    if (pricing == null) {
      throw new NullPointerException("'pricing' must not be null");
    }
    if (pricing.trim().length() == 0) {
      throw new IllegalArgumentException("'pricing' must not be empty");
    }
    return PriceStrategyFactory.getStrategy(pricing).calculate(Collections.unmodifiableList(lines));
  }

  /** {@inheritDoc} */
  @Override
  public IPartialDelivery removeOrderLine(OrderLine ol) {
    if (ol == null) {
      throw new NullPointerException("'tu' must not be null");
    }
    synchronized (lines) {
      boolean removed = lines.remove(ol);
      if (!removed) {
        throw new IllegalArgumentException("Order Line [" + ol + "] not found in Partial Delivery");
      }
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public IPartialDelivery setDeliverySpecification(ISpecification<PartialDeliveryCandidate> rule) {
    if (rule == null) {
      throw new NullPointerException("'rule' must not be null");
    }
    this.specification = rule;
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public double getWeight() {
    double weight = 0;
    for (OrderLine ol : lines) {
      weight = weight + ol.getWeight();
    }
    return weight;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("<PartialDelivery weight=\"" + getWeight() + "\" price=\"" + getPrice() + "\">");
    for (OrderLine ol : lines) {
      s.append(ol);
    }
    s.append("</PartialDelivery>");
    return s.toString();

  }

  /** {@inheritDoc} */
  @Override
  public List<OrderLine> getOrderLines() {
    return Collections.unmodifiableList(lines);
  }

  /** {@inheritDoc} */
  @Override
  public IPartialDelivery setPricingStrategy(String pricing) {
    if (pricing == null) {
      throw new NullPointerException("'pricing' must not be null");
    }
    if (pricing.trim().length() == 0) {
      throw new IllegalArgumentException("'pricing' must not be empty");
    }
    this.pricing = pricing;
    return this;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
