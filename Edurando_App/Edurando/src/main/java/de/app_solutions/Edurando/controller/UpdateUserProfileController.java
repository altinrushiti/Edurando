package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.service.UpdateUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class UpdateUserProfileController {
    private final UpdateUserProfileService updateUserProfileService;

    @GetMapping("/confirmUser")
    public String confirm() {
        return "";
    }



}
