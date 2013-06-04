
package at.edu.hti.shop.domain.pricing;

import java.util.List;

import at.edu.hti.shop.domain.order.OrderLine;

public interface IPriceStrategy {
  
  double calculate(List<OrderLine> lines);
  
}

//---------------------------- Revision History ----------------------------
//$Log$
//
