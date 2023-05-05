package by.kozlov.hibernate.starter.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
