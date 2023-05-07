package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Material;
import by.kozlov.hibernate.starter.exception.DaoException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MaterialDao implements DaoHibernate<Integer,Material> {

    private static final MaterialDao INSTANCE = new MaterialDao();

    private static final String FIND_ALL_HQL = """
            FROM Material M
            """;

    private static final String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE M.id = :id
            """;


    private MaterialDao() {}

    public static MaterialDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(Session session, Material material) {
        return false;
    }

    @Override
    public Optional<Material> findById(Session session, Integer id) {
        try {
            return session.createQuery(FIND_BY_ID_HQL, Material.class).setParameter("id",id)
                    .list().stream().findFirst();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Material> findAll(Session session) {
        try {
            return session.createQuery(FIND_ALL_HQL, Material.class).list();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Session session, Integer id) {
        return false;
    }

    @Override
    public Material save(Session session, Material material) {
        return null;
    }
}
