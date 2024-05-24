package wiki.hf.persistence.converters;

import wiki.hf.domain.*;
import static wiki.hf.domain.ItemType.*;

import jakarta.persistence.*;
import wiki.hf.persistence.exceptions.DataQualityException;

import java.util.Optional;

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
