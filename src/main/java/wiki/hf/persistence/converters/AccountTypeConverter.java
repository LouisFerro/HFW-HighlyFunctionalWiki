package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import static wiki.hf.domain.AccountType.*;

import jakarta.persistence.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import java.util.Optional;

@Converter(autoApply = true)
public class AccountTypeConverter implements AttributeConverter<AccountType, String>
{
    @Override
    public String convertToDatabaseColumn(AccountType accountType) {
        return Optional.ofNullable(accountType)
                       .map(type -> switch (type) {
                           case OWNER -> "OWNER";
                           case ADMINISTRATOR -> "ADMINISTRATOR";
                           case EDITOR -> "EDITOR";
                           case READER -> "READER";
                       })
                       .orElse(null);
    }

    @Override
    public AccountType convertToEntityAttribute(String value) {
        return Optional.ofNullable(value)
                       .map(type -> switch (type) {
                           case "OWNER" -> OWNER;
                           case "ADMINISTRATOR" -> ADMINISTRATOR;
                           case "EDITOR" -> EDITOR;
                           case "READER" -> READER;
                           default -> throw DataQualityException.invalidValue(type, AccountType.class);
                       })
                       .orElse(null);
    }
}
