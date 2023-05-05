package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.WorkersSets;
import by.kozlov.hibernate.starter.exception.DaoException;
import by.kozlov.hibernate.starter.utils.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class WorkersSetsDao implements Dao<Integer, WorkersSets> {

    private static final WorkersSetsDao INSTANCE = new WorkersSetsDao();
    private static final SetDao setDao = SetDao.getInstance();
    private static final WorkerDao workerDao = WorkerDao.getInstance();

    private static final String FIND_ALL = """
            select ws.id, ws.id_set, ws.id_worker, ws.requirement
            from public.workers_sets as ws
            """;
    private static final String FIND_ALL_BY_ID_WORKER = FIND_ALL + """
            where ws.id_worker = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.workers_sets (id_set,id_worker,requirement) 
            VALUES (?, ?, ?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.workers_sets
            WHERE id = ?
            """;

    @Override
    public boolean update(WorkersSets workersSets) {
        return false;
    }

    @Override
    public Optional<WorkersSets> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<WorkersSets> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<WorkersSets> workersSets = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                workersSets.add(buildWorkersSets(result));
            }
            return workersSets;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }


    }

    @SneakyThrows
    public List<WorkersSets> findAllByWorkerId (Integer id) {

        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL_BY_ID_WORKER)) {
            List<WorkersSets> sets = new ArrayList<>();
            statement.setInt(1, id);
            var result = statement.executeQuery();
            while (result.next()) {
                sets.add(buildWorkersSets(result));
            }
            return sets;
        }
    }

    private WorkersSets buildWorkersSets(ResultSet result) throws SQLException {

            var worker = workerDao.findById(result.getInt("id_worker")).orElseThrow();
            var set = setDao.findById(result.getInt("id_set")).orElseThrow();
        /*return new Production(result.getInt("id"),
                worker,set,
                result.getInt("made_sets"),
                result.getTimestamp("date_of_production").toLocalDateTime()
                );*/

            return WorkersSets.builder()
                    .id(result.getObject("id",Integer.class))
                    .set(set).worker(worker)
                    .requirement(result.getObject("requirement",Integer.class))
                    .build();
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
    public WorkersSets save(WorkersSets workersSets) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,workersSets.getSet().getId());
            statement.setInt(2,workersSets.getWorker().getId());
            statement.setInt(3,workersSets.getRequirement());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                workersSets.setId(generatedKeys.getInt("id"));
            return workersSets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static WorkersSetsDao getInstance() {
        return INSTANCE;
    }
}
