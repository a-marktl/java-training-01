
package at.edu.hti.shop.domain.product;

import at.edu.hti.shop.domain.category.ICategory;

public interface IProduct {

  IProduct setCategory(ICategory category);

  ICategory getCategory();

  long getId();

  int getLeadTime();

  String getName();

  double getPrize();

  IProduct setLeadTime(int leadTime);

  double getWeight();

}
