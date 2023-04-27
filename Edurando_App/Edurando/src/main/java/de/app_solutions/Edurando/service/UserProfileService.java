package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserProfileService /*implements UserDetailsService */{
    @Autowired
    private final UserProfileRepository userProfileRepository;

    public List<UserProfile> getAllProfiles() {
        return userProfileRepository.findAll();
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProfileRepository.findUserProfileByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }*/
}
