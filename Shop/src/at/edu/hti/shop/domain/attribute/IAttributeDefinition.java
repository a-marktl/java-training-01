package at.edu.hti.shop.domain.attribute;

public interface IAttributeDefinition {

    String getName();

    void setName( String string );

    boolean isRequired();

    void setRequired( boolean required );

    Object getDefaultValue();

    void setDefaultValue( Object value );

    String getUnit();
}
