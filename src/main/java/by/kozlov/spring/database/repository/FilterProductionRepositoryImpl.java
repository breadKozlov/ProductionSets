package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Production;
import by.kozlov.spring.dto.ProductionFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import static by.kozlov.spring.database.entity.QProduction.production;

import java.util.List;

@RequiredArgsConstructor
public class FilterProductionRepositoryImpl implements FilterProductionRepository{

    private final EntityManager entityManager;

    @Override
    public List<Production> findAllByFilter(ProductionFilter filter) {

        var predicate = QPredicates.builder()
                .add(filter.workerId(),production.worker.id::eq)
                .add(filter.setId(),production.set.id::eq)
                .add(filter.dateOfProduction(),production.dateOfProduction::before)
                .build();
        return new JPAQuery<Production>(entityManager)
                .select(production)
                .from(production)
                .where(predicate)
                .fetch();
    }
}
