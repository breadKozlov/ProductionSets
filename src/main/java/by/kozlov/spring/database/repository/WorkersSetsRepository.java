package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.WorkersSets;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkersSetsRepository extends JpaRepository<WorkersSets,Integer> {

    String FIND_ALL_HQL = """
            FROM WorkersSets W JOIN FETCH W.set JOIN FETCH W.worker JOIN FETCH W.worker.brigade
            """;

    String FIND_BY_ID_WORKER_HQL = FIND_ALL_HQL + """
             WHERE W.worker.id = :id
            """;

    String FIND_BY_ID_HQL = FIND_ALL_HQL + """
             WHERE W.id = :id
            """;

    String FIND_ALL_RELEASED_SETS = """
            SELECT W.set.nameOfSet as name,W.requirement,(SELECT sum(P.madeSets)
             FROM Production P WHERE P.set.nameOfSet = W.set.nameOfSet AND P.worker.id = W.worker.id) FROM WorkersSets W
             where W.worker.id = :id
             ORDER BY name asc
            """;
    @Query(FIND_ALL_RELEASED_SETS)
    List<Object[]> findAllProdSetsById(@Param("id") @NotNull Integer id);

    @Query(FIND_BY_ID_WORKER_HQL)
    List<WorkersSets> findAllByWorkerId(@Param("id") @NotNull Integer idWorker);

    @Override
    @Query(FIND_BY_ID_HQL)
    @NotNull
    Optional<WorkersSets> findById(@Param("id") @NotNull Integer id);

    @Override
    @Query(FIND_ALL_HQL)
    @NotNull
    List<WorkersSets> findAll();
}
