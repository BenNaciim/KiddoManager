package kiddo.kiddomanager.controllers;

import kiddo.kiddomanager.models.Child;
import kiddo.kiddomanager.services.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/child")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @GetMapping("/details")
    public ResponseEntity<Child> getChildDetails(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Child child = childService.retrieveChildDetails(firstName, lastName);
        return Objects.nonNull(child)?ResponseEntity.ok(child):ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addChild(@RequestBody Child child) {
        childService.addChild(child);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/remove")
    public ResponseEntity<HttpStatus> removeChild(@RequestBody Child child) {
        childService.removeChild(child);
        return ResponseEntity.ok().build();
    }
}
