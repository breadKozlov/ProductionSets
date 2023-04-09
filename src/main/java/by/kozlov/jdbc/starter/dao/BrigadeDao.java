package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.entity.Brigade;
import by.kozlov.jdbc.starter.exception.DaoException;
import by.kozlov.jdbc.starter.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrigadeDao implements Dao<Integer, Brigade> {

    private static final BrigadeDao INSTANCE = new BrigadeDao();
    private static final String FIND_ALL = """
            SELECT id, name_of_brigade, phone_number_of_foreman
            FROM public.brigades
            """;

    private static final String UPDATE_SQL = """
            UPDATE public.brigades SET
            name_of_brigade = ?,
            phone_number_of_foreman = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.brigades
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.brigades (name_of_brigade,phone_number_of_foreman) 
            VALUES (?, ?)
            """;


    @Override
    public boolean update(Brigade brigade) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1,brigade.getNameOfBrigade());
            statement.setString(2,brigade.getPhoneNumberOfForeman());
            statement.setInt(3,brigade.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Brigade> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Brigade brigade = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                brigade = buildBrigade(result);
            return Optional.ofNullable(brigade);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Brigade> findAll() {

        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<Brigade> brigades = new ArrayList<>();
            var result = statement.executeQuery();

            while (result.next()) {
                brigades.add(buildBrigade(result));
            }
            return brigades;
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
    public Brigade save(Brigade brigade) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, brigade.getNameOfBrigade());
            statement.setString(2, brigade.getPhoneNumberOfForeman());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                brigade.setId(generatedKeys.getInt("id"));
            return brigade;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Brigade buildBrigade(ResultSet result) throws SQLException {
        return new Brigade(result.getInt("id"),
                result.getString("name_of_brigade"),
                result.getString("phone_number_of_foreman"));
    }

    private BrigadeDao() {}

    public static BrigadeDao getInstance() {
        return INSTANCE;
    }
}
