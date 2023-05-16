package by.kozlov.hibernate.starter.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import by.kozlov.hibernate.starter.entity.BaseEntity;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {
    E save(E entity);

    void delete(K id);

    void update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();
}
