package wiki.hf.persistence.converters;

import static wiki.hf.domain.ActionType.*;
import wiki.hf.domain.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import jakarta.persistence.*;

import java.util.*;

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
