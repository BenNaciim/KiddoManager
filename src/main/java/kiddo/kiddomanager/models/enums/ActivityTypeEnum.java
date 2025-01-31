package kiddo.kiddomanager.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ActivityTypeEnum {
    Arrival("Arrivé"),
    Departure("Départ"),
    Food("Repas/Goûter"),
    Nap("Sieste"),
    Diaper("Changement de couches"),
    care("Soins");
    private final String type;
}
