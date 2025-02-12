package kiddo.kiddomanager.models;

import kiddo.kiddomanager.models.enums.PersonalRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate hireDate;
    private PersonalRoleEnum role;
}
