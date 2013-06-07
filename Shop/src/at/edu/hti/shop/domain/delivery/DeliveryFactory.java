
package at.edu.hti.shop.domain.delivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.edu.hti.shop.domain.order.Order;
import at.edu.hti.shop.domain.order.OrderLine;
import at.edu.hti.shop.domain.pricing.PriceStrategyFactory;
import at.edu.hti.shop.domain.specification.ISpecification;

public final class DeliveryFactory {

  public static final IDelivery getDelivery(Order o, String pricing, ISpecification<PartialDeliveryCandidate> rule) {
    if (o == null) {
      throw new NullPointerException("'o' must not be null");
    }
    if (rule == null) {
      throw new NullPointerException("'rule' must not be null");
    }

    if (pricing == null || pricing.trim().length() == 0) {
      pricing = PriceStrategyFactory.DEFAULT_PRICING_STRATEGY;
    }

    List<OrderLine> lines = o.getOrderLines();

    if (lines.isEmpty()) {
      throw new IllegalArgumentException("Order [" + o + "] does not contain any Order Lines to calculate transports for");
    }

    IDelivery delivery = new Delivery();

    List<IPartialDelivery> parts = partition(lines, rule, pricing, 0);

    for (IPartialDelivery part : parts) {
      delivery.addPartialDelivery(part);
    }

    return delivery;

  }

  private static List<IPartialDelivery> partition(List<OrderLine> lines, ISpecification<PartialDeliveryCandidate> rule, String pricing, int lastRemaining) {
    if (rule == null) {
      throw new NullPointerException("'rule' must not be null");
    }
    if (lines == null) {
      throw new NullPointerException("'lines' must not be null");
    }
    if (lines.isEmpty()) {
      return Collections.emptyList();
    }
    if (pricing == null) {
      throw new NullPointerException("'pricing' must not be null");
    }
    if (pricing.trim().length() == 0) {
      throw new IllegalArgumentException("'pricing' must not be empty");
    }

    List<IPartialDelivery> parts = new ArrayList<IPartialDelivery>();
    List<OrderLine> remaining = new ArrayList<OrderLine>();

    IPartialDelivery partial = new PartialDelivery();
    partial.setDeliverySpecification(rule);
    partial.setPricingStrategy(pricing);

    for (OrderLine ol : lines) {
      try {
        //TODO split ol into several partial deliveries
        partial.addOrderLine(ol);
      } catch (DisapprovedOrderLineException e) {
        System.err.println(e.getMessage());
        remaining.add(ol);
      }
    }

    if (partial.getOrderLines().size() > 0) {
      parts.add(partial);
    }

    if (remaining.size() == lastRemaining) {
      for (OrderLine ol : remaining) {
        System.err.println("Order Line [" + ol + "] cannot be added to Delivery due to unsatisfied delivery rule");
      }
      return parts;
    }

    if (!remaining.isEmpty()) {
      parts.addAll(partition(remaining, rule, pricing, remaining.size()));
    }

    return parts;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
