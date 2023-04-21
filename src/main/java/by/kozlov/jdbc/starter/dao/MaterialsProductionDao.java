package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.entity.MaterialsProduction;
import by.kozlov.jdbc.starter.exception.DaoException;
import by.kozlov.jdbc.starter.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialsProductionDao implements Dao<Integer, MaterialsProduction> {

    private static final MaterialsProductionDao INSTANCE = new MaterialsProductionDao();

    private static final MaterialDao materialDao = MaterialDao.getInstance();
    private static final BrigadeDao brigadeDao = BrigadeDao.getInstance();
    private static final String FIND_ALL = """
            SELECT id,id_set_material,id_brigade,quantity_of_raw_materials,date_of_production
            FROM public.production_materials
            """;

    private static final String UPDATE_SQL = """
            UPDATE public.production_materials SET
            id_set_material = ?,
            id_brigade = ?,
            quantity_of_raw_materials = ?,
            date_of_production = ?,
            WHERE id = ?
            """;

    private static final String FIND_BY_ID = FIND_ALL + """
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM public.production_materials
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO public.production_materials (id_set_material,id_brigade,quantity_of_raw_materials,
            date_of_production) 
            VALUES (?, ?, ?, ?)
            """;
    @Override
    public boolean update(MaterialsProduction materialsProduction) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setInt(1, materialsProduction.getMaterial().getId());
            statement.setInt(2, materialsProduction.getBrigade().getId());
            statement.setDouble(3, materialsProduction.getQuantity());
            statement.setDate(4, Date.valueOf(materialsProduction.getDateOfProduction()));
            statement.setInt(5, materialsProduction.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<MaterialsProduction> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            MaterialsProduction production = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                production = buildMaterialsProduction(result);
            return Optional.ofNullable(production);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<MaterialsProduction> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<MaterialsProduction> productions = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                productions.add(buildMaterialsProduction(result));
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
    public MaterialsProduction save(MaterialsProduction materialsProduction) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, materialsProduction.getMaterial().getId());
            statement.setInt(2, materialsProduction.getBrigade().getId());
            statement.setDouble(3, materialsProduction.getQuantity());
            statement.setDate(4, Date.valueOf(materialsProduction.getDateOfProduction()));
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                materialsProduction.setId(generatedKeys.getInt("id"));
            return materialsProduction;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private MaterialsProduction buildMaterialsProduction(ResultSet result) throws SQLException {

        var material = materialDao.findById(result.getInt("id_set_material")).orElseThrow();
        var brigade = brigadeDao.findById(result.getInt("id_brigade")).orElseThrow();
        return new by.kozlov.jdbc.starter.entity.MaterialsProduction(result.getInt("id"),
                material,brigade,
                result.getDouble("quantity_of_raw_materials"),
                result.getDate("date_of_production").toLocalDate()
        );
    }

    private MaterialsProductionDao() {}

    public static MaterialsProductionDao getInstance() {
        return INSTANCE;
    }
}
