package kiddo.kiddomanager.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "documents")
public class DocumentEntity {
    @Id
    private long id;
    private String title;
    private String description;
    private byte[] document;
    @ManyToOne
    @JoinColumn(name = "email")
    private ParentsEntity parents;
}
