package de.app_solutions.Edurando.service;


import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
public class LoginServiceTest extends PostgresContainer {
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    /*

    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private EmailValidator emailValidator;

    @MockBean
    private EmailSender emailSender;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @MockBean
    private PasswordValidator passwordValidator;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private LoginService loginService;
    @MockBean
    private RegistrationService registrationService;

    @Test
    public void loginUserNotVerifiedTest() {

        RegistrationRequest request = new RegistrationRequest("Student", "Max", "Mustermann", "max.mustermann@stud.th-luebeck.de", "password", "password", true, true);
        registrationService.register(request);
        LoginRequest loginRequest = new LoginRequest("max.mustermann@stud.th-luebeck.de", "Test_123");

        Pair<Boolean, List<String>> rs = loginService.login(loginRequest);
        assertEquals(Pair.of(false, List.of("Password or username is not correct.")),
                rs);
    } */
}
