package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Brigade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrigadeRepository extends JpaRepository<Brigade, Integer> {
}
