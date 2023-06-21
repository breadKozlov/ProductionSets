package by.kozlov.spring.mapper;

public interface OlderMapper<F, T> {

    T mapFrom(F object);
}
