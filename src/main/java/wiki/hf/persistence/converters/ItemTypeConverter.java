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
    public String convertToDatabaseColumn(ItemType itemType) {
        return Optional.ofNullable(itemType)
                       .map(type -> switch (type) {
                            case PAGE -> "PAGE";
                            case SECTION -> "SECTION";
                            case LIST -> "LIST";
                            case VIDEO -> "VIDEO";
                            case IMAGE -> "IMAGE";
                            case TEXT -> "TEXT";
                       }).orElse(null);
    }

    @Override
    public ItemType convertToEntityAttribute(String value) {
        return Optional.ofNullable(value)
                       .map(type -> switch (type) {
                            case "PAGE" -> PAGE;
                            case "SECTION" -> SECTION;
                            case "LIST" -> LIST;
                            case "IMAGE" -> IMAGE;
                            case "VIDEO" -> VIDEO;
                            case "TEXT" -> TEXT;
                            default -> throw DataQualityException.invalidValue(type, ItemType.class);
                       }).orElse(null);
    }
}
