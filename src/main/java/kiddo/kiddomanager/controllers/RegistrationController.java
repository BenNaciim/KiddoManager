package kiddo.kiddomanager.controllers;

import kiddo.kiddomanager.models.Parents;
import kiddo.kiddomanager.models.Personal;
import kiddo.kiddomanager.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/parents")
    public ResponseEntity<String> registerParents(@RequestBody Parents parents) {
        registrationService.registerParents(parents);
        return ResponseEntity.ok("Parents registered successfully");
    }
    @PostMapping("/employee")
    public ResponseEntity<String> registerEmploye(@RequestBody Personal personal) {
        registrationService.registerEmployee(personal);
        return ResponseEntity.ok("Personal registered successfully");
    }
}
