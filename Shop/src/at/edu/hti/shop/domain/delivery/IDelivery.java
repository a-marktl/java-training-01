
package at.edu.hti.shop.domain.delivery;

public interface IDelivery {

  IDelivery addPartialDelivery(IPartialDelivery part);

  IDelivery removePartialDelivery(IPartialDelivery part);

  double getPrice();

  double getWeight();
}

//---------------------------- Revision History ----------------------------
//$Log$
//
