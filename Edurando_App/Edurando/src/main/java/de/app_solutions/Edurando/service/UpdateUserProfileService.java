package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileService {
    private final UserProfileRepository userProfileRepository;
}
