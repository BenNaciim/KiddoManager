package kiddo.kiddomanager.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "Parents")
public class ParentsEntity {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;
    @OneToMany(mappedBy = "parents", cascade = CascadeType.MERGE)
    private List<ChildEntity> children;
}
