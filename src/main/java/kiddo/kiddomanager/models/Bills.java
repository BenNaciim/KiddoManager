package kiddo.kiddomanager.models;

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
public class Bills {
    private LocalDate fromDate;
    private LocalDate toDate;
    private double amount;
    private Parents parents;
}