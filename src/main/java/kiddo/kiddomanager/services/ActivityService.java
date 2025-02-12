package kiddo.kiddomanager.services;

import kiddo.kiddomanager.models.Activity;
import kiddo.kiddomanager.models.mappers.ActivityMapper;
import kiddo.kiddomanager.repositories.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityMapper activityMapper;
    private final ActivityRepository activityRepository;

    public void addActivity(Activity activity) {
        activityRepository.save(activityMapper.mapActivityEntity(activity));
    }
}
