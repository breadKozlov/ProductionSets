package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.exception.DaoException;
import by.kozlov.hibernate.starter.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductionDao implements Dao<Integer, Production> {

    private static final ProductionDao INSTANCE = new ProductionDao();
    private static final WorkerDao workerDao = WorkerDao.getInstance();
    private static final SetDao setDao = SetDao.getInstance();

    private static final String FIND_ALL = """
            SELECT id,id_worker,id_set,made_sets,date_of_production
            FROM public.production
            """;

    private static final String UPDATE_SQL = """
            UPDATE public.production SET
            id_worker = ?,
            id_set = ?,
            made_sets = ?,
            date_of_production = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_WORKER = FIND_ALL + """
            WHERE id_worker = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.production
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.production (id_worker,id_set,made_sets,date_of_production) 
            VALUES (?, ?, ?, ?)
            """;


    @Override
    public boolean update(Production production) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setInt(1,production.getWorker().getId());
            statement.setInt(2,production.getSet().getId());
            statement.setInt(3,production.getMadeSets());
            statement.setDate(4, Date.valueOf(production.getDateOfProduction()));
            statement.setInt(5,production.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Production> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Production production = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                production = buildProduction(result);
            return Optional.ofNullable(production);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Production> findAllByWorkerId(Integer idWorker) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_BY_ID_WORKER)) {
            List<Production> productions = new ArrayList<>();
            statement.setInt(1, idWorker);
            var result = statement.executeQuery();
            while (result.next()) {
                productions.add(buildProduction(result));
            }
            return productions;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Production> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<Production> productions = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                productions.add(buildProduction(result));
            }
            return productions;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }



    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Production save(Production production) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,production.getWorker().getId());
            statement.setInt(2,production.getSet().getId());
            statement.setInt(3,production.getMadeSets());
            statement.setDate(4, Date.valueOf(production.getDateOfProduction()));
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                production.setId(generatedKeys.getInt("id"));
            return production;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Production buildProduction(ResultSet result) throws SQLException {

        var worker = workerDao.findById(result.getInt("id_worker")).orElseThrow();
        var set = setDao.findById(result.getInt("id_set")).orElseThrow();
        /*return new Production(result.getInt("id"),
                worker,set,
                result.getInt("made_sets"),
                result.getTimestamp("date_of_production").toLocalDateTime()
                );*/

        return Production.builder()
                .id(result.getObject("id",Integer.class))
                .worker(worker).set(set)
                .madeSets(result.getObject("made_sets",Integer.class))
                .dateOfProduction(result.getObject("date_of_production",Date.class).toLocalDate())
                .build();
    }

    private ProductionDao() {}

    public static ProductionDao getInstance() {
        return INSTANCE;
    }
}
