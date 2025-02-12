package kiddo.kiddomanager.models;

import kiddo.kiddomanager.models.enums.PersonalRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Personal {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate hireDate;
    private PersonalRoleEnum role;
}
