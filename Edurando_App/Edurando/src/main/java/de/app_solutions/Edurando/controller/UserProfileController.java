package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserProfileController {
    @Autowired
    private final UserProfileService userProfileService;

    @GetMapping("/userProfiles")
    public List<UserProfile> getUserProfile(){
        return userProfileService.getAllProfiles();
    }

}
