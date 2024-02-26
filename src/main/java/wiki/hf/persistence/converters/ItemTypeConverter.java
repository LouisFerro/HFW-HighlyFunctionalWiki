package wiki.hf.persistence.converters;

import static wiki.hf.domain.ItemType.*;
import wiki.hf.domain.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import jakarta.persistence.*;

import java.util.*;

@Converter(autoApply = true)
public class ItemTypeConverter implements AttributeConverter<ItemType, String>
{
    @Override
    public String convertToDatabaseColumn(ItemType itemType)
    {
        return Optional.ofNullable(itemType)
                       .map(type -> switch (type) {
                            case LIST -> "L";
                            case TEXT -> "T";
                            case IMAGE -> "I";
                            case VIDEO -> "V";
                       }).orElse(null);
    }

    @Override
    public ItemType convertToEntityAttribute(String value)
    {
        return Optional.ofNullable(value)
                       .map(v -> switch (v) {
                           case "L" -> LIST;
                           case "T" -> TEXT;
                           case "I" -> IMAGE;
                           case "V" -> VIDEO;
                           default -> throw DataQualityException.invalidValue(v, ItemType.class);
                       }).orElse(null);
    }
}
