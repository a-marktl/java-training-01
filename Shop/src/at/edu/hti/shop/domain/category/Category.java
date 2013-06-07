
package at.edu.hti.shop.domain.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category implements ICategory, Comparable<Category> {

  private final String name;
  private final List<ICategory> subCategories = new ArrayList<ICategory>();

  public Category(String name) {
    if (name == null) {
      throw new NullPointerException("'name' must not be null");
    }
    if (name.trim().length() == 0) {
      throw new IllegalArgumentException("'name' must not be empty");
    }
    this.name = name;
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return name;
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<ICategory> getSubcategories() {
    return Collections.unmodifiableCollection(subCategories);
  }

  /** {@inheritDoc} */
  @Override
  public void addSubcategory(ICategory category) {
    if (category == null) {
      throw new NullPointerException("'category' must not be null");
    }
    if (subCategories.contains(category)) {
      throw new IllegalArgumentException("Category [" + category + "] already is already a sub-category of [" + name + "]");
    }
    subCategories.add(category);
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(Category o) {
    if (o == null) {
      throw new NullPointerException("'o' must not be null");
    }
    return name.compareTo(o.getName());
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    int result = 31;
    result = 31 * result + name.hashCode();
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ICategory)) {
      return false;
    }

    ICategory category = (ICategory) obj;
    if (!category.getName().equals(name)) {
      return false;
    }

    return true;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("<Category name=\"" + name + "\"/>");
    return s.toString();
  }
}
