package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.MaterialsProduction;

public interface Mapper<F, T> {

    T map(F object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }

    default void copy(F fromObject, T toObject) {}
}
