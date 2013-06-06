package at.edu.hti.shop.domain.category;

public interface ICategory {
    String getName();

    void setName( String name );

    Iterable<Category> getSubcategories();

    void addSubcategory( Category category );

    Iterable<Product> getAllProducts();

    Iterable<Product> getProducts();

    void addProduct( Product product );

    Iterable<AttributeDefinition> getAttributeDefinitions();

    Iterable<AttributeDefinition> getAllAttributeDefinitions();

    AttributeDefinition createAttributeDefinition( AttributeType type, String name );
}
