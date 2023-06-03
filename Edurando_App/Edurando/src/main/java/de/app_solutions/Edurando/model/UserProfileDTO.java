package de.app_solutions.Edurando.model;


import java.util.List;

public record UserProfileDTO (
        Long id,
        String firstName,
        String lastName,
        String mobile,
        String profilePictureReference,
        String personalBiography,
        float rating,
        String gender,
        String tutoringLocation,
        String username,
        String role,

        List<Subject> subjects,
        List<Topic> topics,
        List<Rating> ratings,
        Address address
){}
