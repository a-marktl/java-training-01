
package at.edu.hti.shop.domain;

import java.util.List;

public interface IPriceStrategy {
  
  double calculate(List<OrderLine> lines);
  
}

//---------------------------- Revision History ----------------------------
//$Log$
//
