package wiki.hf.service.exceptions;

import lombok.*;

@Getter
public abstract class BaseException extends RuntimeException {
    protected Class model;
    protected Object object;

    public BaseException(Class model, String detail, Object object) {
        super(detail);
        this.model = model;
        this.object = object;
    }
}