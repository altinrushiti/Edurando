package de.app_solutions.Edurando.service;


import de.app_solutions.Edurando.testcontainers.PostgresContainer;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTest  {
    @ClassRule
    public static PostgreSQLContainer<PostgresContainer> postgreSQLContainer = PostgresContainer.getInstance();


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
