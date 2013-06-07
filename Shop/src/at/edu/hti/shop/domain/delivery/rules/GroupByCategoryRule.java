
package at.edu.hti.shop.domain.delivery.rules;

import java.util.List;

import at.edu.hti.shop.domain.category.ICategory;
import at.edu.hti.shop.domain.delivery.PartialDeliveryCandidate;
import at.edu.hti.shop.domain.order.OrderLine;

public class GroupByCategoryRule extends AbstractDeliveryRule {

  /** {@inheritDoc} */
  @Override
  public boolean isSatisfiedBy(PartialDeliveryCandidate candidate) {
    List<OrderLine> existing = candidate.getPartialDelivery().getOrderLines();
    if (existing.isEmpty()) {
      return true;
    }

    ICategory category = existing.get(0).getProduct().getCategory();
    ICategory olCategory = candidate.getOrderLine().getProduct().getCategory();

    if (!olCategory.equals(category)) {
      return false;
    }

    return true;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
