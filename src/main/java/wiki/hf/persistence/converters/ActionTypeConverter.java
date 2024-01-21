package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import static wiki.hf.domain.ActionType.*;

import jakarta.persistence.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import java.util.*;

@Converter(autoApply = true)
public class ActionTypeConverter implements AttributeConverter<ActionType, String>
{
    @Override
    public String convertToDatabaseColumn(ActionType ActionType)
    {
        return Optional.ofNullable(ActionType)
                .map(type -> switch (type) {
                    case CREATE -> "C";
                    case UPDATE -> "U";
                    case DELETE -> "D";
                }).orElse(null);
    }

    @Override
    public ActionType convertToEntityAttribute(String value)
    {
        return Optional.ofNullable(value)
                .map(v -> switch (v) {
                    case "C" -> CREATE;
                    case "U" -> UPDATE;
                    case "D" -> DELETE;
                    default -> throw DataQualityException.invalidValue(v, ActionType.class);
                }).orElse(null);
    }
}
