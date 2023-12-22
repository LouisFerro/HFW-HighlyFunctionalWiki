package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import static wiki.hf.domain.AccountType.*;

import jakarta.persistence.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import java.util.*;

@Converter(autoApply = true)
public class AccountTypeConverter implements AttributeConverter<AccountType, String>
{
    @Override
    public String convertToDatabaseColumn(AccountType accountType)
    {
        return Optional.ofNullable(accountType)
                .map(type -> switch (type) {
                    case USER -> "U";
                    case EDITOR -> "E";
                    case ADMINISTRATOR -> "A";
                    case OWNER -> "O";
                }).orElse(null);
    }

    @Override
    public AccountType convertToEntityAttribute(String value)
    {
        return Optional.ofNullable(value)
                .map(v -> switch (v) {
                    case "U" -> USER;
                    case "E" -> EDITOR;
                    case "A" -> ADMINISTRATOR;
                    case "O" -> OWNER;
                    default -> throw DataQualityException.invalidValue(v, AccountType.class);
                }).orElse(null);
    }
}
