package by.kozlov.spring.database.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> {

    T getId();
    void setId(T id);
}
