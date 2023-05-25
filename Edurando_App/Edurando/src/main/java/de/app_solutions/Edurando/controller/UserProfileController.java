package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import de.app_solutions.Edurando.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final UserProfileRepository userProfileRepository;

    @GetMapping("/profiles")
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getAllUsers();
    }

    @GetMapping("/profile/{id}")
    public UserProfile getUserProfile(@PathVariable Long id) {
        return userProfileService.getUserById(id);
    }

    @GetMapping("/top-users")
    public List<UserProfile> getTopUsers() {
        return userProfileService.showTopUsers();
    }

    @GetMapping("/profileByEmail/{email}")
    public UserProfile getUserProfile(@PathVariable String email) {
        return userProfileService.getUserByEmail(email);
    }

    @GetMapping("/profiles/search/{searchTerm}")
    public ResponseEntity<List<UserProfile>> search(@PathVariable String searchTerm) {
        List<UserProfile> filteredProfiles = userProfileRepository.search(searchTerm);

        return ResponseEntity.ok(filteredProfiles);
    }

}
