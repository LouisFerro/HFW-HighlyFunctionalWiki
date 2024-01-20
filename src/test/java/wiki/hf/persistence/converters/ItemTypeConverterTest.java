package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

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
       var exception = assertThrows(DataQualityException.class, () -> converter.convertToEntityAttribute("X"));
       assertThat(exception).hasMessage("\"X\" is not a valid value for Enumerator ItemType.");
    }
}
