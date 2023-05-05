package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.ShiftHour;
import by.kozlov.hibernate.starter.exception.DaoException;
import by.kozlov.hibernate.starter.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShiftHourDao implements Dao<Integer, ShiftHour>{

    private static final ShiftHourDao INSTANCE = new ShiftHourDao();

    private static final String FIND_ALL = """
            SELECT id, name_of_hour
            FROM public.schedule_hours
            """;

    private static final String UPDATE_SQL = """
            UPDATE public.schedule_hours SET
            name_of_hour = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.schedule_hours
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.schedule_hours (name_of_hour) 
            VALUES (?)
            """;


    @Override
    public boolean update(ShiftHour shiftHour) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1,shiftHour.getNameOfShiftHour());
            statement.setInt(2,shiftHour.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<ShiftHour> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            ShiftHour shiftHour = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                shiftHour = buildShiftHour(result);
            return Optional.ofNullable(shiftHour);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ShiftHour> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<ShiftHour> shiftHours = new ArrayList<>();
            var result = statement.executeQuery();

            while (result.next()) {
                shiftHours.add(buildShiftHour(result));
            }
            return shiftHours;
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
    public ShiftHour save(ShiftHour shiftHour) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, shiftHour.getNameOfShiftHour());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                shiftHour.setId(generatedKeys.getInt("id"));
            return shiftHour;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private ShiftHour buildShiftHour(ResultSet result) throws SQLException {
        return new ShiftHour(result.getInt("id"),
                result.getString("name_of_hour"));
    }

    private ShiftHourDao() {
    }

    public static ShiftHourDao getInstance() {
        return INSTANCE;
    }
}
