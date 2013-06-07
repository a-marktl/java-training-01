
package at.edu.hti.shop.domain.delivery.rules;

import at.edu.hti.shop.domain.delivery.PartialDeliveryCandidate;

public class MaxWeightRule extends AbstractDeliveryRule {

  private double maxWeight;

  public MaxWeightRule(double maxWeight) {
    if (maxWeight <= 0) {
      throw new IllegalArgumentException("'maxWeight' must not be less or equal than 0");
    }
    this.maxWeight = maxWeight;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isSatisfiedBy(PartialDeliveryCandidate candidate) {
    if (candidate == null) {
      throw new NullPointerException("'candidate' must not be null");
    }

    double currentWeight = candidate.getPartialDelivery().getWeight();
    double olWeight = candidate.getOrderLine().getWeight();

    if ((currentWeight + olWeight) > maxWeight) {
      return false;
    }

    return true;
  }

}

//---------------------------- Revision History ----------------------------
//$Log$
//
