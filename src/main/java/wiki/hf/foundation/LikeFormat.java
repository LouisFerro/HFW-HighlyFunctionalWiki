package wiki.hf.foundation;

public interface LikeFormat {

    default String formatExpression(String expression) {
        return (!expression.contains("%")) ? String.format("%s%%", expression)
                : expression;
    }
}
