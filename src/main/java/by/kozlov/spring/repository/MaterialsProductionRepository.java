package by.kozlov.spring.repository;

import by.kozlov.spring.entity.MaterialsProduction;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface MaterialsProductionRepository extends JpaRepository<MaterialsProduction,Integer> {

    String FIND_ALL_HQL = """
            FROM MaterialsProduction M JOIN FETCH M.material JOIN FETCH M.brigade
            """;

    String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE M.id = :id
            """;

    String FIND_BY_ID_BRIGADE_HQL = FIND_ALL_HQL + """
             WHERE M.brigade.id = :id
            """;

    @Query(FIND_BY_ID_BRIGADE_HQL)
    List<MaterialsProduction> findAllByBrigadeId(@Param("id") Integer brigadeId);

    @Override
    @Query(FIND_BY_ID_HQL)
    @NotNull
    Optional<MaterialsProduction> findById(@Param("id") @NotNull Integer id);

    @Override
    @Query(FIND_ALL_HQL)
    @NotNull
    List<MaterialsProduction> findAll();
}
