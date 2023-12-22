package wiki.hf.persistence.exceptions;

public class DataQualityException extends RuntimeException
{
    private DataQualityException(String message)
    {
        super(message);
    }

    public static DataQualityException invalidValue(String value, Class<? extends Enum> enumerator)
    {
        String message = "\"%s\" is not a valid value for Enumerator %s.".formatted(value, enumerator.getSimpleName());
        return new DataQualityException(message);
    }
}
