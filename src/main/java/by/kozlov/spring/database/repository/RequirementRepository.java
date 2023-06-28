package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Requirement;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement,Integer> {

    String FIND_ALL_HQL = """
            FROM Requirement R JOIN FETCH R.set JOIN FETCH R.material
            """;

    String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE R.id = :id
            """;

    String FIND_ALL_BY_ID_SET_HQL = FIND_ALL_HQL + """
             WHERE R.set.id = :id
            """;

    String FIND_SUM_REQ_MAT = """
            SELECT R.material.nameOfMaterial as name,sum(R.totalSets * R.unitCost),(select sum(M.quantity)
            from MaterialsProduction M where M.material.nameOfMaterial = R.material.nameOfMaterial)
            from Requirement R group by name order by name asc
            """;

    @Query(FIND_ALL_BY_ID_SET_HQL)
    List<Requirement> findAllBySetId(@Param("id") @NotNull Integer id);

    @Query(FIND_SUM_REQ_MAT)
    List<Object[]> findSumAllReqMat();

    @Override
    @Query(FIND_BY_ID_HQL)
    @Nonnull
    Optional<Requirement> findById(@Param("id") @Nonnull Integer id);

    @Override
    @Query(FIND_ALL_HQL)
    @Nonnull
    List<Requirement> findAll(@Nonnull Sort sort);
}
