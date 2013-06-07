
package at.edu.hti.shop;

import at.edu.hti.shop.domain.category.Categories;
import at.edu.hti.shop.domain.delivery.DeliveryFactory;
import at.edu.hti.shop.domain.delivery.IDelivery;
import at.edu.hti.shop.domain.delivery.rules.GroupByCategoryRule;
import at.edu.hti.shop.domain.delivery.rules.GroupByLeadTimeRule;
import at.edu.hti.shop.domain.delivery.rules.MaxWeightRule;
import at.edu.hti.shop.domain.order.Order;
import at.edu.hti.shop.domain.order.OrderException;
import at.edu.hti.shop.domain.order.OrderLine;
import at.edu.hti.shop.domain.pricing.PriceStrategyFactory;
import at.edu.hti.shop.domain.product.Product;

public class App {
  public static void main(String[] args) throws OrderException {

    Order o = new Order();

    Product apple = new Product(1, "Apfel", 0.99, 0.25);
    apple.setCategory(Categories.GROCERY);
    apple.setLeadTime(5);

    Product pear = new Product(2, "Birne", 0.69, 0.30);
    pear.setCategory(Categories.GROCERY);
    pear.setLeadTime(5);

    Product peach = new Product(3, "Pfirsch", 1.49, 0.33);
    peach.setCategory(Categories.GROCERY);
    peach.setLeadTime(2);

    Product hammer = new Product(4, "Hammer", 2.99, 1.25);
    hammer.setCategory(Categories.TOOL);
    hammer.setLeadTime(1);

    Product saw = new Product(5, "Saw", 4.89, 0.97);
    saw.setCategory(Categories.TOOL);
    saw.setLeadTime(1);

    Product bobbyCar = new Product(6, "Bobby Car", 49.99, 3.8);
    bobbyCar.setCategory(Categories.TOY);
    bobbyCar.setLeadTime(14);

    o.add(new OrderLine(apple, 100));
    o.add(new OrderLine(pear, 100));
    o.add(new OrderLine(peach, 100));

    o.add(new OrderLine(hammer, 1));
    o.add(new OrderLine(saw, 1));

    o.add(new OrderLine(bobbyCar, 10));

    IDelivery d =
      DeliveryFactory.getDelivery(o,
                                  PriceStrategyFactory.ADD_SHIPPING_COSTS,
                                  new MaxWeightRule(50).and(new GroupByCategoryRule()).and(new GroupByLeadTimeRule()));

    System.out.println(d);

  }
}