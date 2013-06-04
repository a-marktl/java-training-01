
package at.edu.hti.shop.domain.specification;

/**
 * Copyright 2013 SSI Schaefer PEEM GmbH. All Rights reserved. <br />
 * <br />
 * $Id: NotSpecification.java,v 1.1 2013/04/30 05:30:52 marktl Exp $ <br />
 * <br />
 * Implementation of an logical <i>NOT</i> Specification which tests if the given specification is
 * <i>NOT</i> satisfied.
 * 
 * @author marktl
 * @version $Revision: 1.1 $
 * @param <T> Bound Parameter to indicate for which Objects the specification can be used
 */

public final class NotSpecification<T> extends AbstractSpecification<T> {

  private final ISpecification<T> specification;

  public NotSpecification(ISpecification<T> specification) {
    if (specification == null) {
      throw new NullPointerException("'specification' must not be null");
    }
    this.specification = specification;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isSatisfiedBy(T candidate) {
    if (candidate == null) {
      throw new NullPointerException("'candidate' must not be null");
    }
    return !specification.isSatisfiedBy(candidate);
  }
}
