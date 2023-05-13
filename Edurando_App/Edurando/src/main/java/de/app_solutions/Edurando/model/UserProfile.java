package de.app_solutions.Edurando.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String mobile = "";
    private String profilePictureReference = "Edurando_App/Edurando/src/main/resources/p_placeholder.jpg";
    private String personalBiography = "";
    private float rating;
    private String gender = "";
    private String tutoringLocation = "";
    private String username;
    private String password;
    private boolean termsAgreed;
    private boolean privacyAgreed;

    @ManyToMany
    private List<Subject> subjects;

    @ManyToMany
    private List<Topic> topics;

    @ManyToMany
    private List<Rating> ratings;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JsonManagedReference
    private Address address;

    private boolean locked;

    private boolean enabled;

    public UserProfile(String role, String firstName, String lastName,  String email, String password, boolean termsAgreed, boolean privacyAgreed) {
        if (role.equals("Student")) this.role = Role.student;
        else this.role = Role.teacher;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
