
package at.edu.hti.shop.domain.specification;

/**
 * Copyright 2013 SSI Schaefer PEEM GmbH. All Rights reserved. <br />
 * <br />
 * $Id: AbstractSpecification.java,v 1.1 2013/04/30 05:30:52 marktl Exp $ <br />
 * <br />
 * <b>Abstract</b> Base Implementation of {@link ISpecification} providing logical concatination
 * methods <i>and, or, not</i>.
 * 
 * @author marktl
 * @version $Revision: 1.1 $
 * @param <T> Bound Parameter to indicate for which Objects the specification can be used
 */

public abstract class AbstractSpecification<T> implements ISpecification<T> {

  /** {@inheritDoc} */
  @Override
  public ISpecification<T> and(final ISpecification<T> specification) {
    if (specification == null) {
      throw new NullPointerException("'specification' must not be null");
    }
    return new AndSpecification<T>(this, specification);
  }

  /** {@inheritDoc} */
  @Override
  public ISpecification<T> or(final ISpecification<T> specification) {
    if (specification == null) {
      throw new NullPointerException("'specification' must not be null");
    }
    return new OrSpecification<T>(this, specification);
  }

  /** {@inheritDoc} */
  @Override
  public ISpecification<T> not(ISpecification<T> specification) {
    if (specification == null) {
      throw new NullPointerException("'specification' must not be null");
    }
    return new NotSpecification<T>(specification);
  }

}