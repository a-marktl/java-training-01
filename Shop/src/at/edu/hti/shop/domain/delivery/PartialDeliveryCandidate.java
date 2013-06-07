
package at.edu.hti.shop.domain.delivery;

import at.edu.hti.shop.domain.order.OrderLine;

public class PartialDeliveryCandidate {

  private final IPartialDelivery partial;
  private final OrderLine ol;

  public PartialDeliveryCandidate(IPartialDelivery partial, OrderLine ol) {
    if (ol == null) {
      throw new NullPointerException("'ol' must not be null");
    }
    if (partial == null) {
      throw new NullPointerException("'partial' must not be null");
    }

    this.partial = partial;
    this.ol = ol;
  }

  public OrderLine getOrderLine() {
    return ol;
  }

  public IPartialDelivery getPartialDelivery() {
    return partial;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
