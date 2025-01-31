package kiddo.kiddomanager.models;

import kiddo.kiddomanager.models.enums.ActivityTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Activity {
    private ActivityTypeEnum activityType;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
