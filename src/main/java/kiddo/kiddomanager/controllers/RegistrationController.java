package kiddo.kiddomanager.controllers;

import kiddo.kiddomanager.models.Parents;
import kiddo.kiddomanager.models.Personal;
import kiddo.kiddomanager.services.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/parents")
    public ResponseEntity<HttpStatus> registerParents(@RequestBody Parents parents) {
        return Objects.nonNull(registrationService.registerParents(parents))? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/employee")
    public ResponseEntity<HttpStatus> registerEmployee(@RequestBody Personal personal) {
        return Objects.nonNull(registrationService.registerEmployee(personal))? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
