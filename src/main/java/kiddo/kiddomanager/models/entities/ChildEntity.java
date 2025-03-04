package kiddo.kiddomanager.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "children")
public class ChildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    @Transient
    private int age;
    private LocalDate dateOfRegistration;
    private byte[] picture;
    @OneToMany(mappedBy = "child")
    private List<ActivityEntity> tasks;
    @ManyToOne
    @JoinColumn(name = "email")
    private ParentsEntity parents;

    @PostLoad
    public void calculateAge() {
        if (dateOfBirth != null) {
            this.age = LocalDate.now().getYear() - dateOfBirth.getYear();
        }
    }

}