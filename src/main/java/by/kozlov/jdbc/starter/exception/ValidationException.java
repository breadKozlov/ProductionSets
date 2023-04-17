package by.kozlov.jdbc.starter.exception;

import by.kozlov.jdbc.starter.validator.Error;
import lombok.Getter;

import java.util.List;

public class ValidationException extends RuntimeException {

    @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}