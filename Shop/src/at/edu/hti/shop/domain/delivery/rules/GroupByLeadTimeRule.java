
package at.edu.hti.shop.domain.delivery.rules;

import java.util.List;

import at.edu.hti.shop.domain.delivery.PartialDeliveryCandidate;
import at.edu.hti.shop.domain.order.OrderLine;

public class GroupByLeadTimeRule extends AbstractDeliveryRule {

  /** {@inheritDoc} */
  @Override
  public boolean isSatisfiedBy(PartialDeliveryCandidate candidate) {
    List<OrderLine> existing = candidate.getPartialDelivery().getOrderLines();
    if (existing.isEmpty()) {
      return true;
    }

    int leadTime = existing.get(0).getProduct().getLeadTime();
    int olLeadTime = candidate.getOrderLine().getProduct().getLeadTime();

    if (olLeadTime != leadTime) {
      return false;
    }

    return true;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
