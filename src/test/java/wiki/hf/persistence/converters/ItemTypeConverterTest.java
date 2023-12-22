package wiki.hf.persistence.converters;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wiki.hf.domain.*;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class ItemTypeConverterTest
{
    private ItemTypeConverter converter;
    @BeforeEach
    void Setup()
    {
        converter = new ItemTypeConverter();
    }

    @Test
    void convertValidAttributeToColumn()
    {
        assertThat(converter.convertToDatabaseColumn(ItemType.LIST)).isEqualTo("L");
        assertThat(converter.convertToDatabaseColumn(ItemType.TEXT)).isEqualTo("T");
        assertThat(converter.convertToDatabaseColumn(ItemType.IMAGE)).isEqualTo("I");
        assertThat(converter.convertToDatabaseColumn(ItemType.VIDEO)).isEqualTo("V");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void convertValidColumnToAttribute()
    {
        assertThat(converter.convertToEntityAttribute("L")).isEqualTo(ItemType.LIST);
        assertThat(converter.convertToEntityAttribute("T")).isEqualTo(ItemType.TEXT);
        assertThat(converter.convertToEntityAttribute("I")).isEqualTo(ItemType.IMAGE);
        assertThat(converter.convertToEntityAttribute("V")).isEqualTo(ItemType.VIDEO);
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void throwExceptionForInvalidColumn()
    {
       var exception = assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("X"));
       assertThat(exception).hasMessage("\"X\" is not a valid value for Enumerator ItemType.");
    }
}
