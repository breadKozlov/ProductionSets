package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.entity.Production;
import by.kozlov.jdbc.starter.entity.Worker;
import by.kozlov.jdbc.starter.entity.WorkersSets;
import by.kozlov.jdbc.starter.exception.DaoException;
import by.kozlov.jdbc.starter.utils.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
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

        return new WorkersSets(result.getInt("id"),
                set,worker,
                result.getInt("requirement")
        );
    }


    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public WorkersSets save(WorkersSets workersSets) {
        return null;
    }

    public static WorkersSetsDao getInstance() {
        return INSTANCE;
    }
}
