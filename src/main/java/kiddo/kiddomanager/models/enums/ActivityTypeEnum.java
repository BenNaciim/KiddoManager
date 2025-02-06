package kiddo.kiddomanager.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActivityTypeEnum {
    Arrival("Arrivé"),
    Departure("Départ"),
    Food("Repas/Goûter"),
    Nap("Sieste"),
    Diaper("Changement de couches"),
    Care("Soin"),
    Custom("");

    private String type;


    public static ActivityTypeEnum customType(String customType) {
        ActivityTypeEnum customActivity = ActivityTypeEnum.Custom;
        customActivity.type = customType;
        return customActivity;
    }
}
