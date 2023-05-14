package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.EditPasswordRequest;
import de.app_solutions.Edurando.model.EditPersonalDataRequest;
import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class UserProfileController {
    private final UserProfileService userProfileService;


    @GetMapping("/profiles")
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getAllUsers();
    }

    @CrossOrigin
    @GetMapping("/profile/{id}")
    public UserProfile getUserProfile(@PathVariable Long id) {
        return userProfileService.getUserById(id);
    }

    @PutMapping("/updatePersonalData")
    public String updatePersonalData(@RequestBody EditPersonalDataRequest editPersonalDataRequest) {
        return userProfileService.editPersonalData(editPersonalDataRequest);
    }

    @PutMapping("/editPassword")
    public ResponseEntity<String> editPassword(@RequestBody EditPasswordRequest passwordRequest) {


        Pair<Boolean, String> result = userProfileService.editPassword(passwordRequest);

        if (result.getFirst()) {
            return ResponseEntity.ok().body(result.getSecond());
        } else {
            return ResponseEntity.badRequest().body(result.getSecond());
        }
    }




}
