package kiddo.kiddomanager.repositories;

import kiddo.kiddomanager.models.entities.ParentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentsRepository extends JpaRepository<ParentsEntity, String> {
    ParentsEntity findByEmail(String email);
}
