
package at.edu.hti.shop.domain.delivery;

import java.util.List;

import at.edu.hti.shop.domain.order.OrderLine;
import at.edu.hti.shop.domain.specification.ISpecification;

public interface IPartialDelivery {

  IPartialDelivery setDeliverySpecification(ISpecification<PartialDeliveryCandidate> rule);

  IPartialDelivery setPricingStrategy(String pricing);

  IPartialDelivery addOrderLine(OrderLine ol) throws DisapprovedOrderLineException;

  IPartialDelivery removeOrderLine(OrderLine ol);

  double getPrice();

  double getWeight();

  List<OrderLine> getOrderLines();

}

//---------------------------- Revision History ----------------------------
//$Log$
//
