package at.edu.hti.shop.domain.product;

import at.edu.hti.shop.domain.attribute.AttributeDefinition;
import at.edu.hti.shop.domain.category.Category;

import java.util.Map;

public interface IProduct {
    Map<AttributeDefinition, Object> getAttributeValues();

    void setAttribute( AttributeDefinition attributeDefinition, Object value );

    Category getCategory();
}
