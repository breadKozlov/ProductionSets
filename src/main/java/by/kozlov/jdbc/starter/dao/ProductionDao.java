package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.entity.Production;
import by.kozlov.jdbc.starter.exception.DaoException;
import by.kozlov.jdbc.starter.utils.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionDao {

    private static final ProductionDao INSTANCE = new ProductionDao();

    private static String FIND_ALL = """
            select p.id_worker,p.id_set,p.made_sets,p.date_of_production
            from public.production as p
            order by p.id_worker;
            """;

    public List<Production> findAll() {

        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<Production> productions = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                productions.add(new Production(
                        result.getInt("id_worker"),
                        result.getInt("id_set"),
                        result.getInt("made_sets"),
                        result.getDate("date_of_production")
                ));
            }
            return productions;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    private ProductionDao() {}

    public static ProductionDao getInstance() {
        return INSTANCE;
    }
}
