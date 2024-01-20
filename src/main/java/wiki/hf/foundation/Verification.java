package wiki.hf.foundation;

import java.util.Optional;
import java.util.function.Predicate;

public class Verification {

    public static Predicate<? super Object> nullFilter = o -> o == null;
    public static Predicate<? super Object> nonNullFilter = nullFilter.negate();

    public static <T> T notNull(T argument) {
        return notNull(argument, "argument");
    }

    public static <T> T notNull(T argument, String argumentName) {
        if(argument == null) {
            throw new IllegalArgumentException("%s must not be null!".formatted(argumentName));
        }
        return argument;

        /*
        return Optional.ofNullable(argument)
                       .orElseThrow(() -> new IllegalArgumentException("%s must not be null!".formatted(argumentName)));
        */
    }
}
