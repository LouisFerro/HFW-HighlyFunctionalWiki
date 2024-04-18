package wiki.hf.service.exceptions;

import lombok.*;

@Getter
public abstract class BaseException extends RuntimeException {
    protected Class model;
    protected String type;
    protected Object object;

    public BaseException(Class model, String type, Object object, String detail) {
        super(detail);
        this.model = model;
        this.type = type;
        this.object = object;
    }

    public BaseException(Class model, String type, String detail) {
        super(detail);
        this.model = model;
        this.type = type;
    }
}