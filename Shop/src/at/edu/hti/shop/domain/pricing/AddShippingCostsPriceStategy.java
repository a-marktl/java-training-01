
package at.edu.hti.shop.domain.pricing;

import java.util.List;

import at.edu.hti.shop.domain.order.OrderLine;

public class AddShippingCostsPriceStategy implements IPriceStrategy {

  /** {@inheritDoc} */
  @Override
  public double calculate(List<OrderLine> lines) {
    double sum = 0;

    for (OrderLine line : lines) {
      sum += line.getPrice();
    }

    if (sum > 10) {
      return sum;
    }

    return sum + 5;
  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
