package by.kozlov.hibernate.starter.dao;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface DaoHibernate<K,E> {

    boolean update(Session session, E e);

    Optional<E> findById(Session session,K id);

    List<E> findAll(Session session);

    boolean delete(Session session,K id);

    E save(Session session,E e);
}
