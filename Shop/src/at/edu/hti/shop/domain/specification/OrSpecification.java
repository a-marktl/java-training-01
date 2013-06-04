
package at.edu.hti.shop.domain.specification;

/**
 * Copyright 2013 SSI Schaefer PEEM GmbH. All Rights reserved. <br />
 * <br />
 * $Id: OrSpecification.java,v 1.1 2013/04/30 05:30:52 marktl Exp $ <br />
 * <br />
 * Implementation of an logical <i>OR</i> Specification which tests if at least one of the two
 * specifications provided is satisfied.
 * 
 * @author marktl
 * @version $Revision: 1.1 $
 * @param <T> Bound Parameter to indicate for which Objects the specification can be used
 */

public final class OrSpecification<T> extends AbstractSpecification<T> {
  private final ISpecification<T> specification1;
  private final ISpecification<T> specification2;

  public OrSpecification(ISpecification<T> specification1, ISpecification<T> specification2) {
    if (specification1 == null) {
      throw new NullPointerException("'spec1' must not be null");
    }
    if (specification2 == null) {
      throw new NullPointerException("'spec2' must not be null");
    }
    if (specification1.equals(specification2)) {
      throw new IllegalStateException("Given Specifications are equal");
    }

    this.specification1 = specification1;
    this.specification2 = specification2;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isSatisfiedBy(T candidate) {
    if (candidate == null) {
      throw new NullPointerException("'candidate' must not be null");
    }
    return specification1.isSatisfiedBy(candidate) || specification2.isSatisfiedBy(candidate);
  }

}