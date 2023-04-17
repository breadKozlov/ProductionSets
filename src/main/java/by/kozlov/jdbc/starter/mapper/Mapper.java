package by.kozlov.jdbc.starter.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
