package by.kozlov.hibernate.starter.dao;

import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.exception.DaoException;
import by.kozlov.hibernate.starter.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequirementDao implements Dao<Integer, Requirement> {

    private static final RequirementDao INSTANCE = new RequirementDao();
    private static final SetDao setDao = SetDao.getInstance();
    private static final MaterialDao materialDao = MaterialDao.getInstance();

    private static final String FIND_ALL = """
            SELECT id,id_set,id_set_material,unit_cost,total_sets
            FROM public.requirement
            """;

    private static final String UPDATE_SQL = """
            UPDATE public.requirement SET
            id_set = ?,
            id_set_material = ?,
            unit_cost = ?,
            total_sets = ?,
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SET = FIND_ALL + """
            WHERE id_set = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.requirement
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.requirement (id_set,id_set_material,unit_cost,total_sets) 
            VALUES (?, ?, ?, ?)
            """;

    @Override
    public boolean update(Requirement requirement) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setInt(1,requirement.getSet().getId());
            statement.setInt(2,requirement.getMaterial().getId());
            statement.setDouble(3,requirement.getUnitCost());
            statement.setInt(4, requirement.getTotalSets());
            statement.setInt(5,requirement.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Requirement> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Requirement requirement = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                requirement = buildRequirement(result);
            return Optional.ofNullable(requirement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Requirement> findAllBySetId(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            List<Requirement> requirements = new ArrayList<>();
            statement.setInt(1, id);
            var result = statement.executeQuery();
            while (result.next()) {
                requirements.add(buildRequirement(result));
            }
            return requirements;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<Requirement> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<Requirement> requirements = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                requirements.add(buildRequirement(result));
            }
            return requirements;
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
    public Requirement save(Requirement requirement) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,requirement.getSet().getId());
            statement.setInt(2,requirement.getMaterial().getId());
            statement.setDouble(3,requirement.getUnitCost());
            statement.setInt(4, requirement.getTotalSets());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                requirement.setId(generatedKeys.getInt("id"));
            return requirement;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Requirement buildRequirement(ResultSet result) throws SQLException {

        var set = setDao.findById(result.getInt("id_set")).orElseThrow();
        var material = materialDao.findById(result.getInt("id_set_material")).orElseThrow();
        return new Requirement(result.getInt("id"),
                set,material,
                result.getDouble("unit_cost"),
                result.getInt("total_sets"));
    }

    private RequirementDao() {}

    public static RequirementDao getInstance() {
        return INSTANCE;
    }
}
