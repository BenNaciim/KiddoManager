package kiddo.kiddomanager.repositories;

import kiddo.kiddomanager.models.entities.ChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<ChildEntity, Long> {
    ChildEntity findChildEntityByFirstNameAndLastName(String firstName, String lastName);

}
