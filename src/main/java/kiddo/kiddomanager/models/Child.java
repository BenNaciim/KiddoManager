package kiddo.kiddomanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Child {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int age;
    private LocalDate dateOfRegistration;
    private List<Activity> activities;
}
