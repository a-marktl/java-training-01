
package at.edu.hti.shop.domain.pricing;

import java.util.HashMap;
import java.util.Map;

public class PriceStrategyFactory {
  public static final String DEFAULT_PRICING_STRATEGY = "DEFAULT";
  public static final String ADD_SHIPPING_COSTS = "ADD-SHIPPING-COSTS";

  private static Map<String, Class<? extends IPriceStrategy>> repository = new HashMap<String, Class<? extends IPriceStrategy>>();

  static {
    //TODO this should be loaded from some configuration
    repository.put("DEFAULT", DefaultPriceStrategy.class);
    repository.put("ADD-SHIPPING-COSTS", AddShippingCostsPriceStategy.class);
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
