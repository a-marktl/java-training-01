
package at.edu.hti.shop.domain;

public class OrderLine {
  private Product product;
  private int amount;
  private final Order order;

  public OrderLine(Order o, Product product, int amount) {
    if (o == null) {
      throw new NullPointerException("'o' must not be null");
    }
    if (product == null) {
      throw new NullPointerException("'product' must not be null");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("'amount' must not be less or equal to 0");
    }
    this.order = o;
    this.product = product;
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("'amount' must not be less than 0");
    }
    if (amount == 0) {
      order.remove(this);
    }

    this.amount = amount;
  }

  public Product getProduct() {
    return product;
  }

  public double calcPrize() {
    return amount * product.getPrize();
  }

  @Override
  public String toString() {
    return "OrderLine [" + product + ", " + amount + "]";
  }

  public double getPrice() {
    return amount * product.getPrize();
  }

}
