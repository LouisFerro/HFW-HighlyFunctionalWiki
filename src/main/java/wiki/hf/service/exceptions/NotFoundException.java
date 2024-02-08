package wiki.hf.service.exceptions;

import lombok.*;

@Getter
public abstract class NotFoundException extends RuntimeException {
    protected Object key;

    public NotFoundException(Object key, String detail) {
        super(detail);
        this.key = key;
    }
}
