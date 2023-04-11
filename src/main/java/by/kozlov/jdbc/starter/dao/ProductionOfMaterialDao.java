package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.entity.Material;
import by.kozlov.jdbc.starter.entity.Production;
import by.kozlov.jdbc.starter.entity.ProductionOfMaterial;
import by.kozlov.jdbc.starter.exception.DaoException;
import by.kozlov.jdbc.starter.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductionOfMaterialDao implements Dao<Integer, ProductionOfMaterial> {

    private static final ProductionOfMaterialDao INSTANCE = new ProductionOfMaterialDao();

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
    public boolean update(ProductionOfMaterial productionOfMaterial) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setInt(1,productionOfMaterial.getMaterial().getId());
            statement.setInt(2,productionOfMaterial.getBrigade().getId());
            statement.setDouble(3,productionOfMaterial.getQuantity());
            statement.setTimestamp(4, Timestamp.valueOf(productionOfMaterial.getDateOfProduction()));
            statement.setInt(5,productionOfMaterial.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<ProductionOfMaterial> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            ProductionOfMaterial production = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                production = buildProductionOfMaterial(result);
            return Optional.ofNullable(production);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ProductionOfMaterial> findAll() {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL)) {
            List<ProductionOfMaterial> productions = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next()) {
                productions.add(buildProductionOfMaterial(result));
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
    public ProductionOfMaterial save(ProductionOfMaterial productionOfMaterial) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,productionOfMaterial.getMaterial().getId());
            statement.setInt(2,productionOfMaterial.getBrigade().getId());
            statement.setDouble(3,productionOfMaterial.getQuantity());
            statement.setTimestamp(4, Timestamp.valueOf(productionOfMaterial.getDateOfProduction()));
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                productionOfMaterial.setId(generatedKeys.getInt("id"));
            return productionOfMaterial;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private ProductionOfMaterial buildProductionOfMaterial(ResultSet result) throws SQLException {

        var material = materialDao.findById(result.getInt("id_set_material")).orElseThrow();
        var brigade = brigadeDao.findById(result.getInt("id_brigade")).orElseThrow();
        return new ProductionOfMaterial(result.getInt("id"),
                material,brigade,
                result.getDouble("quantity_of_raw_materials"),
                result.getTimestamp("date_of_production").toLocalDateTime()
        );
    }

    private ProductionOfMaterialDao() {}

    public static ProductionOfMaterialDao getInstance() {
        return INSTANCE;
    }
}
