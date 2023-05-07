package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.EditPersonalDataRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserProfileController {
    private final UserProfileService userProfileService;


    @GetMapping("/profiles")
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getAllUsers();
    }

    @PostMapping("/updatePersonalData")
    public String updatePersonalData(EditPersonalDataRequest editPersonalDataRequest) {
        return userProfileService.editPersonalData(editPersonalDataRequest);
    }

}
