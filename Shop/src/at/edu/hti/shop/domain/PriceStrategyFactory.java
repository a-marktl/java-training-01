
package at.edu.hti.shop.domain;

import java.util.HashMap;
import java.util.Map;

public class PriceStrategyFactory {

  private static Map<String, Class<? extends IPriceStrategy>> repository = new HashMap<String, Class<? extends IPriceStrategy>>();

  static {
    try {
      repository.put("DEFAULT", Class.forName("at.edu.hti.shop.domain.DefaultPriceStrategy").asSubclass(IPriceStrategy.class));
      repository.put("ADD-SHIPPING-COSTS", Class.forName("at.edu.hti.shop.domain.AddShippingCostsPriceStategy").asSubclass(IPriceStrategy.class));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  public static IPriceStrategy getStrategy(String id) {
    if (id == null) {
      throw new NullPointerException("'id' must not be null");
    }
    if (id.trim().length() == 0) {
      throw new IllegalArgumentException("'id' must not be empty");
    }

    Class<? extends IPriceStrategy> strategy = repository.get(id);

    if (strategy == null) {
      throw new IllegalArgumentException("'strategy' with id [" + id + "] not supported");
    }

    try {
      return strategy.newInstance();
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }

  }
}

//---------------------------- Revision History ----------------------------
//$Log$
//
