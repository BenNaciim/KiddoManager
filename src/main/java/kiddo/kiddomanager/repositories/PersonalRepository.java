package kiddo.kiddomanager.repositories;

import kiddo.kiddomanager.models.entities.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<PersonalEntity, String> {
}
