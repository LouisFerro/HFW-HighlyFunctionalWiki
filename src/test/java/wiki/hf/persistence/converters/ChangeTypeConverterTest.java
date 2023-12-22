package wiki.hf.persistence.converters;

import wiki.hf.domain.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wiki.hf.persistence.exceptions.DataQualityException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ChangeTypeConverterTest
{
    private ChangeTypeConverter converter;
    @BeforeEach
    void Setup()
    {
        converter = new ChangeTypeConverter();
    }

    @Test
    void convertValidAttributeToColumn()
    {
        assertThat(converter.convertToDatabaseColumn(ChangeType.CREATE)).isEqualTo("C");
        assertThat(converter.convertToDatabaseColumn(ChangeType.EDIT)).isEqualTo("E");
        assertThat(converter.convertToDatabaseColumn(ChangeType.DELETE)).isEqualTo("D");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void convertValidColumnToAttribute()
    {
        assertThat(converter.convertToEntityAttribute("C")).isEqualTo(ChangeType.CREATE);
        assertThat(converter.convertToEntityAttribute("E")).isEqualTo(ChangeType.EDIT);
        assertThat(converter.convertToEntityAttribute("D")).isEqualTo(ChangeType.DELETE);
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void throwExceptionForInvalidColumn()
    {
        var exception = assertThrows(DataQualityException.class, () -> converter.convertToEntityAttribute("X"));
        assertThat(exception).hasMessage("\"X\" is not a valid value for Enumerator ChangeType.");
    }
}