package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.entity.Material;
import by.kozlov.jdbc.starter.exception.DaoException;
import by.kozlov.jdbc.starter.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialDao implements Dao<Integer, Material> {

    private static final MaterialDao INSTANCE = new MaterialDao();


    private static final String FIND_ALL = """
            SELECT sm.id, sm.name_of_material, description
            FROM public.set_materials as sm
            """;

    private static final String UPDATE_SQL = """
            UPDATE public.set_materials SET
            name_of_material = ?,
            description = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE sm.id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.set_materials as sm
            WHERE sm.id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.set_materials (name_of_material,description) 
            VALUES (?, ?)
            """;





    @Override
    public boolean update(Material material) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1,material.getNameOfMaterial());
            statement.setString(2,material.getDescription());
            statement.setInt(3,material.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Material> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Material material = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                material = buildMaterial(result);
            return Optional.ofNullable(material);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Material> findAll() {

        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<Material> materials = new ArrayList<>();
            var result = statement.executeQuery();

            while (result.next()) {
                materials.add(buildMaterial(result));
            }
            return materials;
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
    public Material save(Material material) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, material.getNameOfMaterial());
            statement.setString(2, material.getDescription());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                material.setId(generatedKeys.getInt("id"));
            return material;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Material buildMaterial(ResultSet result) throws SQLException {
        return new Material(result.getInt("id"),
                result.getString("name_of_material"),
                result.getString("description"));
    }

    private MaterialDao() {}

    public static MaterialDao getInstance() {
        return INSTANCE;
    }
}
