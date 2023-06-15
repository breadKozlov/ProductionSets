package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Production;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionRepository extends JpaRepository<Production,Integer> {

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

    @Query(FIND_SUM_ALL_SETS)
    List<Object[]> findSumAllProdSets();

    List<Production> findAllByWorkerId(Integer idWorker, Sort sort);

    @Override
    @Query(FIND_BY_ID_HQL)
    @NotNull
    Optional<Production> findById(@Param("id") @NotNull Integer id);

    @Override
    @Query(FIND_ALL_HQL)
    @NotNull
    Page<Production> findAll(@NotNull Pageable page);
}
