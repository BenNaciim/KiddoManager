package kiddo.kiddomanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Parents {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Child> children;
}
