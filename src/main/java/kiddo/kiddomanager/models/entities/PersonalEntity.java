package kiddo.kiddomanager.models.entities;

import kiddo.kiddomanager.models.enums.PersonalRoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalEntity {
    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private PersonalRoleEnum role;
}
