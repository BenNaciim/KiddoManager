package kiddo.kiddomanager.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import kiddo.kiddomanager.models.Child;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParentsEntity {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @OneToMany(mappedBy = "parents", cascade = CascadeType.MERGE)
    private List<ChildEntity> children;
}
