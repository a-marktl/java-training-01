
package at.edu.hti.shop.domain;

import java.util.List;

public class DefaultPriceStrategy implements IPriceStrategy {

  /** {@inheritDoc} */
  @Override
  public double calculate(List<OrderLine> lines) {
    double sum = 0;
    for (OrderLine line : lines) {
      sum += line.getPrice();
    }
    return sum;
  }

}
//---------------------------- Revision History ----------------------------
//$Log$
//
