package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.entity.Brigade;
import by.kozlov.jdbc.starter.entity.Production;
import by.kozlov.jdbc.starter.entity.Schedule;
import by.kozlov.jdbc.starter.entity.ShiftHour;
import by.kozlov.jdbc.starter.exception.DaoException;
import by.kozlov.jdbc.starter.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScheduleDao implements Dao<Integer, Schedule> {

    private static final ScheduleDao INSTANCE = new ScheduleDao();
    private static final ShiftHourDao shiftHourDao = ShiftHourDao.getInstance();
    private static final BrigadeDao brigadeDao = BrigadeDao.getInstance();

    private static final String FIND_ALL = """
            SELECT id,name_month,id_hour,id_brigade
            FROM public.schedule
            """;

    private static final String UPDATE_SQL = """
            UPDATE public.schedule SET
            name_month = ?,
            id_hour = ?,
            id_brigade = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.schedule
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.schedule (name_month,id_hour,id_brigade) 
            VALUES (?, ?, ?)
            """;



    private ScheduleDao() {}

    public static ScheduleDao getInstance() {
        return INSTANCE;
    }
    @Override
    public boolean update(Schedule schedule) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1,schedule.getNameMonth());
            statement.setInt(2,schedule.getShiftHour().getId());
            statement.setInt(3,schedule.getBrigade().getId());
            statement.setInt(4,schedule.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Schedule> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Schedule schedule = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                schedule = buildSchedule(result);
            return Optional.ofNullable(schedule);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Schedule> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<Schedule> schedules = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                schedules.add(buildSchedule(result));
            }
            return schedules;
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
    public Schedule save(Schedule schedule) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,schedule.getNameMonth());
            statement.setInt(2,schedule.getShiftHour().getId());
            statement.setInt(3,schedule.getBrigade().getId());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                schedule.setId(generatedKeys.getInt("id"));
            return schedule;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Schedule buildSchedule(ResultSet result) throws SQLException {

        var shiftHour = shiftHourDao.findById(result.getInt("id_hour")).orElseThrow();
        var brigade = brigadeDao.findById(result.getInt("id_brigade")).orElseThrow();
        return new Schedule(result.getInt("id"),
                result.getString("name_month"),
                shiftHour,brigade
        );
    }
}
