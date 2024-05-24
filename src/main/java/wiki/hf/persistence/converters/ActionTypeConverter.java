package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import static wiki.hf.domain.ActionType.*;

import jakarta.persistence.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import java.util.Optional;

@Converter(autoApply = true)
public class ActionTypeConverter implements AttributeConverter<ActionType, String>
{
    @Override
    public String convertToDatabaseColumn(ActionType ActionType) {
        return Optional.ofNullable(ActionType)
                       .map(type -> switch (type) {
                           case CREATION -> "CREATION";
                           case UPDATE -> "UPDATE";
                           case DELETION -> "DELETION";
                       })
                       .orElse(null);
    }

    @Override
    public ActionType convertToEntityAttribute(String value) {
        return Optional.ofNullable(value)
                       .map(type -> switch (type) {
                           case "CREATION" -> CREATION;
                           case "UPDATE" -> UPDATE;
                           case "DELETION" -> DELETION;
                           default -> throw DataQualityException.invalidValue(type, ActionType.class);
                       })
                       .orElse(null);
    }
}
