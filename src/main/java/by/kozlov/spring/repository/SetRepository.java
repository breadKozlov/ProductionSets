package by.kozlov.spring.repository;

import by.kozlov.spring.entity.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<Set,Integer> {
}
