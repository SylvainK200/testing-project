package exceptionhandler.exceptions;

import lombok.Getter;

import java.util.Optional;

@Getter
public class ObjectNotFoundException extends RuntimeException {
    private final String message;

    public ObjectNotFoundException( String message) {
        super(message);
        this.message = message;
    }
}
