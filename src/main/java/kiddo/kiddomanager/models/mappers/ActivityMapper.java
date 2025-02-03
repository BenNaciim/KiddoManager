package kiddo.kiddomanager.models.mappers;

import kiddo.kiddomanager.models.Activity;
import kiddo.kiddomanager.models.entities.ActivityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    ActivityEntity mapActivityEntity(Activity activity);
    Activity mapActivity(ActivityEntity activityEntity);

}
