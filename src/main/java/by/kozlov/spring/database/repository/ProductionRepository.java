package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Production;
import io.micrometer.common.lang.NonNullApi;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionRepository extends JpaRepository<Production,Integer>,
FilterProductionRepository, QuerydslPredicateExecutor<Production> {

    String FIND_ALL_HQL = """
            FROM Production P JOIN FETCH P.set JOIN FETCH P.worker JOIN FETCH P.worker.brigade
            """;

    String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE P.id = :id
            """;

    String FIND_SUM_ALL_SETS = """
            SELECT R.set.nameOfSet as name,avg(R.totalSets),(select sum(P.madeSets)
            from Production P where P.set.nameOfSet = R.set.nameOfSet)
            from Requirement R group by name order by name asc
            """;

    String FIND_BY_ID_WORKER_HQL = FIND_ALL_HQL + """
             WHERE P.worker.id = :id
            """;
    @Query(FIND_SUM_ALL_SETS)
    List<Object[]> findSumAllProdSets();

    @Query(FIND_BY_ID_WORKER_HQL)
    List<Production> findAllByWorkerId(@Param("id") @NotNull Integer idWorker, Sort sort);

    @Override
    @Query(FIND_BY_ID_HQL)
    @Nonnull
    Optional<Production> findById(@Param("id") @Nonnull Integer id);

    @Override
    @Query(FIND_ALL_HQL)
    @Nonnull
    Page<Production> findAll(@Nonnull Pageable page);
}
