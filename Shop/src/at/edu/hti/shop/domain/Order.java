
package at.edu.hti.shop.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

  private List<OrderLine> lines = new ArrayList<OrderLine>();
  private String strategyId = "DEFAULT";

  public void setPriceStrategy(String strategy) {
    if (strategy == null) {
      throw new NullPointerException("'strategy' must not be null");
    }
    if (strategy.trim().length() == 0) {
      throw new IllegalArgumentException("'strategy' must not be empty");
    }
    this.strategyId = strategy;
  }

  public synchronized boolean add(OrderLine e) {
    if (e == null) {
      return false;
    }
    return lines.add(e);
  }

  public synchronized void remove(OrderLine e) {
    int idx = getOrderLineIndex(e.getProduct());
    if (idx < 0) {
      throw new IllegalStateException("Order Line for product [" + e.getProduct() + "] not found in order");
    }
    lines.remove(idx);
  }

  public double calcPrize() {

    return PriceStrategyFactory.getStrategy(strategyId).calculate(Collections.unmodifiableList(lines));
  }

  @Override
  public String toString() {
    return super.toString() + " \n =>" + calcPrize();
  }

  private int getOrderLineIndex(Product p) {
    if (p == null) {
      throw new NullPointerException("'p' must not be null");
    }

    int i = 0;
    for (OrderLine line : lines) {
      if (!line.getProduct().equals(p)) {
        i++;
      } else {
        return i;
      }
    }
    return -1;
  }
}
