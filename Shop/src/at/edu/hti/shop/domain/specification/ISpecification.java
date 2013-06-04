
package at.edu.hti.shop.domain.specification;

/**
 * Interface for Specification Pattern. The specification pattern is used to decouple the design of
 * requirements, fulfillment, and validation and allows you to make your system definitions more
 * clear and declarative. <br />
 * <br />
 * see: <a
 * href="http://martinfowler.com/apsupp/spec.pdf">http://martinfowler.com/apsupp/spec.pdf</a><br />
 * 
 * @param <T> Bound Parameter to indicate for which Objects the specification can be used
 */

public interface ISpecification<T> {

  /**
   * Test if the given candidate <i>o</i> satisfies the specification. <br />
   * 
   * @param candidate candidate to check specification against, never null
   * @return true in case specification is satisfied otherwise false
   * @throws NullPointerException in case <i>candidate</i> was null
   */
  boolean isSatisfiedBy(T candidate);

  /**
   * concatinate additional specification to <i>this</i> using logical <i>AND</i> operation
   * 
   * @param specification to concatinate, never null
   * @return new Specification representing logical AND operation
   * @throws NullPointerException in case <i>specification</i> was null
   */
  ISpecification<T> and(ISpecification<T> specification);

  /**
   * concatinate additional specification to <i>this</i> using logical <i>OR</i> operation
   * 
   * @param specification to concatinate, never null
   * @return new Specification representing logical OR operation
   * @throws NullPointerException in case <i>specification</i> was null
   */
  ISpecification<T> or(ISpecification<T> specification);

  /**
   * negate specification using logical <i>NOT</i> operation
   * 
   * @param specification to negate, never null
   * @return new Specification representing logical NOT operation
   * @throws NullPointerException in case <i>specification</i> was null
   */
  ISpecification<T> not(ISpecification<T> specification);

}