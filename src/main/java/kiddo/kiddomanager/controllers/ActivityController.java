package kiddo.kiddomanager.controllers;

import kiddo.kiddomanager.models.Activity;
import kiddo.kiddomanager.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addActivity(@RequestBody Activity activity){
        activityService.addActivity(activity);
        return ResponseEntity.ok().build();

    }
}
