package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.MaterialsProduction;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    List<MaterialsProduction> findAllByBrigadeId(Integer brigadeId, Pageable page);

    @Override
    @Query(FIND_BY_ID_HQL)
    @Nonnull
    Optional<MaterialsProduction> findById(@Param("id") @Nonnull Integer id);

    @Override
    @Query(FIND_ALL_HQL)
    @Nonnull
    Page<MaterialsProduction> findAll(@Nonnull Pageable pageable);
}
