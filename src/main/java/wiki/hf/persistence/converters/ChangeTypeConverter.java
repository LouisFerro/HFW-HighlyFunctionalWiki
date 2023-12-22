package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import static wiki.hf.domain.ChangeType.*;

import jakarta.persistence.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import java.util.*;

@Converter(autoApply = true)
public class ChangeTypeConverter implements AttributeConverter<ChangeType, String>
{
    @Override
    public String convertToDatabaseColumn(ChangeType changeType)
    {
        return Optional.ofNullable(changeType)
                .map(type -> switch (type) {
                    case CREATE -> "C";
                    case EDIT -> "E";
                    case DELETE -> "D";
                }).orElse(null);
    }

    @Override
    public ChangeType convertToEntityAttribute(String value)
    {
        return Optional.ofNullable(value)
                .map(v -> switch (v) {
                    case "C" -> CREATE;
                    case "E" -> EDIT;
                    case "D" -> DELETE;
                    default -> throw DataQualityException.invalidValue(v, ChangeType.class);
                }).orElse(null);
    }
}
