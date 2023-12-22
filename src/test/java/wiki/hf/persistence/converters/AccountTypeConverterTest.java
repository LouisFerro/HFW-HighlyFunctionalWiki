package wiki.hf.persistence.converters;

import wiki.hf.domain.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountTypeConverterTest
{
    private AccountTypeConverter converter;
    @BeforeEach
    void Setup()
    {
        converter = new AccountTypeConverter();
    }

    @Test
    void convertValidAttributeToColumn()
    {
        assertThat(converter.convertToDatabaseColumn(AccountType.USER)).isEqualTo("U");
        assertThat(converter.convertToDatabaseColumn(AccountType.EDITOR)).isEqualTo("E");
        assertThat(converter.convertToDatabaseColumn(AccountType.ADMINISTRATOR)).isEqualTo("A");
        assertThat(converter.convertToDatabaseColumn(AccountType.OWNER)).isEqualTo("O");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void convertValidColumnToAttribute()
    {
        assertThat(converter.convertToEntityAttribute("U")).isEqualTo(AccountType.USER);
        assertThat(converter.convertToEntityAttribute("E")).isEqualTo(AccountType.EDITOR);
        assertThat(converter.convertToEntityAttribute("A")).isEqualTo(AccountType.ADMINISTRATOR);
        assertThat(converter.convertToEntityAttribute("O")).isEqualTo(AccountType.OWNER);
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void throwExceptionForInvalidColumn()
    {
        var exception = assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("X"));
        assertThat(exception).hasMessage("\"X\" is not a valid value for Enumerator AccountType.");
    }
}