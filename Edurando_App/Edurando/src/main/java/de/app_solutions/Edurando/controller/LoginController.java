package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.LoginRequest;
import de.app_solutions.Edurando.model.RegistrationRequest;
import de.app_solutions.Edurando.service.LoginService;
import de.app_solutions.Edurando.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1")
@Data
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {

    private final LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Pair<Boolean, String> response = loginService.login(request);
        if (response.getFirst()) return ResponseEntity.ok(response.getSecond());
        else return ResponseEntity.badRequest().body(response.getSecond());
    }

}
