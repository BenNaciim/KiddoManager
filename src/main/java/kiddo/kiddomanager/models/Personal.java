package kiddo.kiddomanager.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate hireDate;
    private PersonalRoleEnum role;
}
