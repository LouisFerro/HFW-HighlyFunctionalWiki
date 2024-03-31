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
        assertThat(converter.convertToDatabaseColumn(ItemType.PAGE)).isEqualTo("PAGE");
        assertThat(converter.convertToDatabaseColumn(ItemType.SECTION)).isEqualTo("SECTION");
        assertThat(converter.convertToDatabaseColumn(ItemType.LIST)).isEqualTo("LIST");
        assertThat(converter.convertToDatabaseColumn(ItemType.TEXT)).isEqualTo("TEXT");
        assertThat(converter.convertToDatabaseColumn(ItemType.IMAGE)).isEqualTo("IMAGE");
        assertThat(converter.convertToDatabaseColumn(ItemType.VIDEO)).isEqualTo("VIDEO");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void convertValidColumnToAttribute()
    {
        assertThat(converter.convertToEntityAttribute("PAGE")).isEqualTo(ItemType.PAGE);
        assertThat(converter.convertToEntityAttribute("SECTION")).isEqualTo(ItemType.SECTION);
        assertThat(converter.convertToEntityAttribute("LIST")).isEqualTo(ItemType.LIST);
        assertThat(converter.convertToEntityAttribute("TEXT")).isEqualTo(ItemType.TEXT);
        assertThat(converter.convertToEntityAttribute("IMAGE")).isEqualTo(ItemType.IMAGE);
        assertThat(converter.convertToEntityAttribute("VIDEO")).isEqualTo(ItemType.VIDEO);
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void throwExceptionForInvalidColumn()
    {
       var exception = assertThrows(DataQualityException.class, () -> converter.convertToEntityAttribute("X"));
       assertThat(exception).hasMessage("\"X\" is not a valid value for Enumerator ItemType.");
    }
}
