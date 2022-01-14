package repository;

import entities.Trans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransRepository extends JpaRepository<Trans, String> {
}