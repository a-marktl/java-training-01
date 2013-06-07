
package at.edu.hti.shop.domain.category;

public interface ICategory {

  String getName();

  Iterable<ICategory> getSubcategories();

  void addSubcategory(ICategory category);

}
