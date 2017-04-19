package goomba.controller.exception;

import java.util.Set;

/**
 * Created by Gino on 18/04/2017.
 */
public class ErrorResponse{
    private int errorCode;
    private String message;
    private Set<String> invalidFields;

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Set<String> getInvalidFields() {
        return invalidFields;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setInvalidFields(Set<String> invalidFields) {
        this.invalidFields = invalidFields;
    }
}

