package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import static wiki.hf.domain.TokenType.*;

import jakarta.persistence.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import java.util.Optional;

@Converter(autoApply = true)
public class TokenTypeConverter implements AttributeConverter<TokenType, String>
{
    @Override
    public String convertToDatabaseColumn(TokenType tokenType) {
        return Optional.ofNullable(tokenType)
                       .map(type -> switch (type) {
                           case API -> "API";
                           case CONFIRMATION -> "CONFIRMATION";
                           case RESET -> "RESET";
                       })
                       .orElse(null);
    }

    @Override
    public TokenType convertToEntityAttribute(String value) {
        return Optional.ofNullable(value)
                       .map(type -> switch (type) {
                           case "API" -> API;
                           case "CONFIRMATION" -> CONFIRMATION;
                           case "RESET" -> RESET;
                           default -> throw DataQualityException.invalidValue(type, TokenType.class);
                       })
                       .orElse(null);
    }
}