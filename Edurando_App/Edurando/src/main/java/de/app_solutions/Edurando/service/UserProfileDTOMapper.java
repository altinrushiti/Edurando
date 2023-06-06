package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.model.UserProfileDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserProfileDTOMapper implements Function<UserProfile, UserProfileDTO> {
    @Override
    public UserProfileDTO apply(UserProfile userProfile) {
        return new UserProfileDTO(
                userProfile.getId(),
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getMobile(),
                userProfile.getProfilePictureReference(),
                userProfile.getPersonalBiography(),
                userProfile.getRating(),
                userProfile.getGender(),
                userProfile.getTutoringLocation(),
                userProfile.getUsername(),
                userProfile.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining("")),
                userProfile.getSubjects(),
                userProfile.getTopics(),
                userProfile.getRatings(),
                userProfile.getAddress()
        );
    }
}
