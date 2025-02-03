package kiddo.kiddomanager.controllers;

import kiddo.kiddomanager.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/validate/subscription")
    public ResponseEntity<String> validateSubscription(@RequestParam("email") String email) {
        adminService.validateSubscription(email);
        return ResponseEntity.status(HttpStatus.OK).body("Subscription validated");
    }
}
