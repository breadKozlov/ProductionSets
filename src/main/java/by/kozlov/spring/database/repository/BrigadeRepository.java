package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Brigade;

import by.kozlov.spring.database.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrigadeRepository extends JpaRepository<Brigade, Integer> {
}
