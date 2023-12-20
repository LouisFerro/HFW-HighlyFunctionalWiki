package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import static wiki.hf.domain.ChangeType.*;

import jakarta.persistence.*;
import java.util.*;

@Converter
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
                    default -> throw new IllegalArgumentException("\"%s\" is not a valid value for Enumerator ChangeType".formatted(v));
                }).orElse(null);
    }
}
