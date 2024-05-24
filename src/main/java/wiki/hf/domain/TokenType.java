package wiki.hf.domain;

public enum TokenType
{
    API(30),
    CONFIRMATION(5),
    RESET(15);

    private final int length;

    TokenType(int length)
    {
        this.length = length;
    }
}
