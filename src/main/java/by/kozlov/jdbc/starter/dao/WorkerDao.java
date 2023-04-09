package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.entity.Brigade;
import by.kozlov.jdbc.starter.entity.Worker;
import by.kozlov.jdbc.starter.exception.DaoException;
import by.kozlov.jdbc.starter.utils.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkerDao implements Dao<Integer, Worker>{

    private static final WorkerDao INSTANCE = new WorkerDao();

    private static final String FIND_ALL = """
            SELECT w.id,w.name_worker,w.surname_worker,w.speciality,w.rank,w.experience,
            b.name_of_brigade,b.phone_number_of_foreman
            FROM public.workers w
            LEFT JOIN public.brigades b on b.id = w.brigade_number
            """;

    private static final String UPDATE_SQL = """
            UPDATE public.worker SET
            name_worker = ?,
            surname_worker = ?,
            speciality = ?,
            rank = ?,
            experience = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE w.id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.workers
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.workers (name_worker,surname_worker,speciality,rank,experience,brigade_number) 
            VALUES (?, ?, ?, ?, ?, ?)
            """;
    @Override
    public boolean update(Worker worker) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1,worker.getNameOfWorker());
            statement.setString(2,worker.getSurnameOfWorker());
            statement.setString(3,worker.getSpeciality());
            statement.setInt(4,worker.getRank());
            statement.setInt(5,worker.getExperience());
            statement.setInt(6,worker.getBrigade().getId());
            statement.setInt(7,worker.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Worker> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Worker worker = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                worker = buildWorker(result);
            return Optional.ofNullable(worker);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Worker> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<Worker> workers = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                workers.add(buildWorker(result));
            }
            return workers;
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
    public Worker save(Worker worker) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, worker.getNameOfWorker());
            statement.setString(2, worker.getSurnameOfWorker());
            statement.setString(3, worker.getSpeciality());
            statement.setInt(4, worker.getRank());
            statement.setInt(5, worker.getExperience());
            statement.setInt(6, worker.getBrigade().getId());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                worker.setId(generatedKeys.getInt("id"));
            return worker;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Worker buildWorker(ResultSet result) throws SQLException {

        var brigade = new Brigade(result.getInt("id"),
                result.getString("name_of_brigade"),
                result.getString("phone_number_of_foreman"));
        return new Worker(result.getInt("id"),
                result.getString("name_worker"),
                result.getString("surname_worker"),
                result.getString("speciality"),
                result.getInt("rank"),
                result.getInt("experience"),
                brigade);
    }

    private WorkerDao() {}

    public static WorkerDao getInstance() {
        return INSTANCE;
    }
}
