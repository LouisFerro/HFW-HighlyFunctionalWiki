package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class ActionTypeConverterTest
{
    private ActionTypeConverter converter;
    @BeforeEach
    void Setup()
    {
        converter = new ActionTypeConverter();
    }

    @Test
    void convertValidAttributeToColumn()
    {
        assertThat(converter.convertToDatabaseColumn(ActionType.CREATE)).isEqualTo("C");
        assertThat(converter.convertToDatabaseColumn(ActionType.UPDATE)).isEqualTo("U");
        assertThat(converter.convertToDatabaseColumn(ActionType.DELETE)).isEqualTo("D");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void convertValidColumnToAttribute()
    {
        assertThat(converter.convertToEntityAttribute("C")).isEqualTo(ActionType.CREATE);
        assertThat(converter.convertToEntityAttribute("U")).isEqualTo(ActionType.UPDATE);
        assertThat(converter.convertToEntityAttribute("D")).isEqualTo(ActionType.DELETE);
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void throwExceptionForInvalidColumn()
    {
        var exception = assertThrows(DataQualityException.class, () -> converter.convertToEntityAttribute("X"));
        assertThat(exception).hasMessage("\"X\" is not a valid value for Enumerator ActionType.");
    }
}