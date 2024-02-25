package wiki.hf.service.exceptions;

import lombok.*;

@Getter
public abstract class BaseException extends RuntimeException {
    protected Object key;
    protected Class model;

    public BaseException(Class model, String detail, Object key) {
        super(detail);
        this.model = model;
        this.key = key;
    }
}