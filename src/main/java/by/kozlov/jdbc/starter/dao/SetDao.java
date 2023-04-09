package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.entity.Brigade;
import by.kozlov.jdbc.starter.entity.Set;
import by.kozlov.jdbc.starter.exception.DaoException;
import by.kozlov.jdbc.starter.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SetDao implements Dao<Integer, Set>{

    private static final SetDao INSTANCE = new SetDao();

    private static final String FIND_ALL = """
            SELECT id, name_of_set, num_of_parts, rate_of_set
            FROM public.sets_for_cars
            """;

    private static final String UPDATE_SQL = """
            UPDATE public.sets_for_cars SET
            name_of_set = ?,
            name_of_parts = ?
            rate_of_set = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.sets_for_cars
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.sets_for_cars (name_of_set, num_of_parts, rate_of_set) 
            VALUES (?, ?, ?)
            """;

    @Override
    public boolean update(Set set) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1,set.getNameOfSet());
            statement.setInt(2,set.getNumberOfPartsIncluded());
            statement.setDouble(3,set.getRateOfSet());
            statement.setInt(4,set.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Set> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Set set = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                set = buildSet(result);
            return Optional.ofNullable(set);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Set> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<Set> sets = new ArrayList<>();
            var result = statement.executeQuery();

            while (result.next()) {
                sets.add(buildSet(result));
            }
            return sets;
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
    public Set save(Set set) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, set.getNameOfSet());
            statement.setInt(2, set.getNumberOfPartsIncluded());
            statement.setDouble(3,set.getRateOfSet());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                set.setId(generatedKeys.getInt("id"));
            return set;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Set buildSet(ResultSet result) throws SQLException {
        return new Set(result.getInt("id"),
                result.getString("name_of_set"),
                result.getInt("num_of_parts"),
                result.getDouble("rate_of_set"));
    }

    private SetDao() {}

    public static SetDao getInstance() {
        return INSTANCE;
    }
}
