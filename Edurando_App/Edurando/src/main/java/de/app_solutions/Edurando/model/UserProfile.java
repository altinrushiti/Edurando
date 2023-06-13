package de.app_solutions.Edurando.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String profilePictureReference = "../assets/p_placeholder.png";
    private String personalBiography = "";
    private float rating;
    private String gender = "";
    private String tutoringLocation = "";
    private String username;
    private String password;
    private boolean termsAgreed;
    private boolean privacyAgreed;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver")
    private List<ChatMessage> chatMessages;

    public UserProfile(String student, String krish, String kalra, String s, String test123, List<Subject> subjects1, boolean b) {
    }

    public List<ChatMessage> getChatMessages() {
        if (chatMessages == null) {
            chatMessages = new ArrayList<>();
        }
        return chatMessages;
    }

    @ElementCollection
    private List<Long> chatReceivers;
    public List<Long> getChatReceivers() {
        if (chatReceivers == null) {
            chatReceivers = new ArrayList<>();
        }
        return chatReceivers;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Topic> topics;

    @ManyToMany
    private List<Rating> ratings;

    @OneToOne
    private ConfirmationCode confirmationCode;
   /* @OneToMany
    private List<ConfirmationToken> confirmationToken;
    public List<ConfirmationToken> getConfirmationToken() {
        if (confirmationToken == null) {
            confirmationToken = new ArrayList<>();
        }
        return confirmationToken;
    }

    */
    @Enumerated(EnumType.STRING)
    private Role role;

   // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    //@Column(name = "conf_user", unique = true, nullable = false)
    //private ConfirmationToken confirmationToken;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    private boolean locked;

    private boolean enabled;



    public UserProfile(String role, String firstName, String lastName,  String email, String password) {
        if (role.equals("Student")) this.role = Role.student;
        else this.role = Role.teacher;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = email;
        this.password = password;
        this.address = new Address();
    }

    public UserProfile(String role, String firstName, String lastName,  String email, String password,  boolean enabled) {
        if (role.equals("Student")) this.role = Role.student;
        else this.role = Role.teacher;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = email;
        this.password = password;
        this.address = new Address();
        this.enabled = enabled;
    }

    public UserProfile(String role, String firstName, String lastName,  String email, String password, boolean enabled,List<Subject> subjects, List<Topic>topics, float rating, String personalBiography) {
        if (role.equals("Student")) this.role = Role.student;
        else this.role = Role.teacher;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = email;
        this.password = password;
        this.address = new Address();
        this.enabled = enabled;
        this.password = password;
        this.subjects = subjects;
        this.topics = topics;
        this.rating = rating;
        this.personalBiography = personalBiography;
    }

    public UserProfile(long fromUserId) {

        this.id = fromUserId;
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


}
