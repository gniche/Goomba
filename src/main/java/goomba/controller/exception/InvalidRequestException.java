package goomba.controller.exception;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gino on 18/04/2017.
 */
public class InvalidRequestException extends Exception {

    private final Set<String> invalidFields;

    public InvalidRequestException(String message, String invalidField) {
        super(message);
        invalidFields = new HashSet<>();
        invalidFields.add(invalidField);
    }

    public InvalidRequestException(String message, Set<String> invalidFields) {
        super(message);
        this.invalidFields = invalidFields;
    }

    public Set<String> getInvalidFields() {
        return invalidFields;
    }
}
