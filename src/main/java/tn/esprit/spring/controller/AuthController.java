package tn.esprit.spring.controller;


import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.dto.user.AccountResponse;
import tn.esprit.spring.dto.user.ActiveAccount;
import tn.esprit.spring.dto.user.JwtLogin;
import tn.esprit.spring.dto.user.JwtSignUp;
import tn.esprit.spring.dto.user.LoginResponse;
import tn.esprit.spring.dto.user.Mail;
import tn.esprit.spring.dto.user.NewPassword;
import tn.esprit.spring.dto.user.UserActive;
import tn.esprit.spring.entities.Code;
import tn.esprit.spring.entities.ConfirmationToken;
import tn.esprit.spring.entities.RefreshToken;
import tn.esprit.spring.entities.ResetPassword;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.RoleEn;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.exception.TokenRefreshException;
import tn.esprit.spring.repository.ConfirmationTokenRepository;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.jwt.JwtUtils;
import tn.esprit.spring.security.jwt.TokenRefreshRequest;
import tn.esprit.spring.security.jwt.TokenRefreshResponse;
import tn.esprit.spring.security.jwt.TokenService;
import tn.esprit.spring.security.sevices.RefreshTokenService;
import tn.esprit.spring.service.email.EmailService;
import tn.esprit.spring.service.user.UserServiceAuth;
import tn.esprit.spring.service.user.UserSevice;
import tn.esprit.spring.util.user.UserCode;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;





@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder ;
  
  @Autowired
  UserSevice userService;
  
  @Autowired
  UserServiceAuth userServiceAuth;
  
  @Autowired
  JwtUtils jwtUtils;
  
  @Autowired
  private TokenService tokenService;
  
  @Autowired
  RefreshTokenService refreshTokenService;
  
  @Autowired
  private ConfirmationTokenRepository confirmationTokenRepository;

  @Autowired
  private EmailService emailService;
  
  @Value("google.id")
  private String idUser;
  


   @PostMapping("/signin")
   public LoginResponse logIn(@RequestBody JwtLogin jwtLogin){  
    return tokenService.login(jwtLogin);
}


@PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();
    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }

  @PostMapping("/signup")
  public AccountResponse createUser(@RequestBody JwtSignUp jwtSignUp){
	  
      AccountResponse accountResponse = new AccountResponse();
      boolean result = userService.ifEmailExist(jwtSignUp.getEmail());
      if(result){
          accountResponse.setResult(0);
      } else {
          String myCode = UserCode.getCode();
          User user = new User();
          user.setEmail(jwtSignUp.getEmail());
          user.setUsername(jwtSignUp.getUsername());
          user.setFirstname(jwtSignUp.getFirstname());
          user.setLastname(jwtSignUp.getLastname());
          user.setAddress(jwtSignUp.getAddress());
          user.setPassportid(jwtSignUp.getPassportid());
          user.setPassword(encoder.encode(jwtSignUp.getPassword()));
          user.setActive(0);
          Set<String> strRoles = jwtSignUp.getRole();
          Set<Role> roles = new HashSet<>();
          if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEn.ROLE_EMPLOYEE)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
          } else {
            strRoles.forEach(role -> {
              switch (role) {
              case "admin":
                Role adminRole = roleRepository.findByName(RoleEn.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);

                break;
              case "company":
                Role comanyRole = roleRepository.findByName(RoleEn.ROLE_COMPANY)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(comanyRole);

                break;
              default:
                Role userRole = roleRepository.findByName(RoleEn.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
              }
            });
          user.setRoles(roles);
          userRepository.save(user);
          ConfirmationToken confirmationToken = new ConfirmationToken(user);
          confirmationTokenRepository.save(confirmationToken);
          Mail mail = new Mail(jwtSignUp.getEmail(),myCode);
          emailService.sendCodeByEmail(mail);
          Code code = new Code();
          code.setCode(myCode);
          user.setCode(code);
          userService.addUser(user);
          accountResponse.setResult(1);
      }
      }
      return accountResponse;
      
  }

  @PostMapping("/active")
  public UserActive getActiveUser(@RequestBody JwtLogin jwtLogin){
      String enPassword = userService.getPasswordByEmail(jwtLogin.getEmail());  // from DB
      boolean result = encoder.matches(jwtLogin.getPassword(),enPassword); // Sure
      UserActive userActive = new UserActive();
      if (result){
          int act = userService.getUserActive(jwtLogin.getEmail());
          if(act == 0){
              String code = UserCode.getCode();
              Mail mail = new Mail(jwtLogin.getEmail(),code);
              emailService.sendCodeByEmail(mail);
              User user = userService.getUserByMail(jwtLogin.getEmail());
              user.getCode().setCode(code);
              userService.editUser(user);
          }
          userActive.setActive(act);
      } else {
          userActive.setActive(-1);
      }
      return userActive;
  }

  @PostMapping("/activated")
  public AccountResponse activeAccount(@RequestBody ActiveAccount activeAccount){
      User user = userService.getUserByMail(activeAccount.getMail());
      AccountResponse accountResponse = new AccountResponse();
      if(user.getCode().getCode().equals(activeAccount.getCode())){
          user.setActive(1);
          userService.editUser(user);
          accountResponse.setResult(1);
      } else {
          accountResponse.setResult(0);
      }
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setTo(user.getEmail());
      mailMessage.setSubject("Complete Registration!");
      mailMessage.setFrom("bttravel2022@gmail.com");
      mailMessage.setText("Congratuations ! Your Account has been activated  and email is verified");
      emailService.sendEmail(mailMessage);
      return accountResponse;
  }
  
  @PostMapping("/checkemail")
  public AccountResponse resetPasswordEmail (@RequestBody ResetPassword resetPassword){
	  
	  //boolean result = this.userService.ifEmailExist(resetPassword.getEmail());
	  User user = this.userService.getUserEmail(resetPassword.getEmail());
	  AccountResponse accountResponse = new AccountResponse();
	  if(user != null){
		  
		  String code = UserCode.getCode();
		  Mail mail = new Mail(resetPassword.getEmail(),code);
		  emailService.sendCodeByEmail(mail);
		  user.getCode().setCode(code);
		  this.userService.editUser(user);
		  accountResponse.setResult(1);
		  
	  }
	  else{
		  accountResponse.setResult(0);
		  
	  }
	  return accountResponse ;
  }
  
  @PostMapping("/resetPassword")
  public AccountResponse resetPassword(@RequestBody NewPassword newPassword){
      User user = this.userService.getUserByMail(newPassword.getEmail());
      AccountResponse accountResponse = new AccountResponse();
      if(user != null){
          if(user.getCode().getCode().equals(newPassword.getCode())){
              user.setPassword(encoder.encode(newPassword.getPassword()));
              userService.addUser(user);
              accountResponse.setResult(1);
          } else {
              accountResponse.setResult(0);
          }
      } else {
          accountResponse.setResult(0);
      }
      return accountResponse;
  }
  
  /*
  @PostMapping("/social/google")
  public LoginResponse loginWithGoogle(@RequestBody TokenDto tokenDto) throws IOException {
      NetHttpTransport transport = new NetHttpTransport();
      JacksonFactory factory = JacksonFactory.getDefaultInstance();
      GoogleIdTokenVerifier.Builder ver =
              new GoogleIdTokenVerifier.Builder(transport,factory)
                      .setAudience(Collections.singleton(idUser));
      GoogleIdToken googleIdToken = GoogleIdToken.parse(ver.getJsonFactory(),tokenDto.getToken());
      GoogleIdToken.Payload payload = googleIdToken.getPayload();
      return login(payload.getEmail());
  }

 
  @PostMapping("/social/facebook")
  public LoginResponse loginWithFacebook(@RequestBody TokenDto tokenDto){
      Facebook facebook = new FacebookTemplate(tokenDto.getToken());
      String [] data = {"email","name","picture"};
      User userFacebook = facebook.fetchObject("me",User.class,data);
      return login(userFacebook.getEmail());

  }

  private LoginResponse login(String email){
      boolean result = userService.ifEmailExist(email); // t   // f
      if(!result){
          User user = new User();
          user.setEmail(email);
          user.setPassword(encoder.encode("kasdjhfkadhsY776ggTyUU65khaskdjfhYuHAwjñlji"));
          user.setActive(1);
          List<Role> authorities = (List<Role>) user.getRoles();
          user.getRoles().add(authorities.get(0));
          userService.addUser(user);
      }
      JwtLogin jwtLogin = new JwtLogin();
      jwtLogin.setEmail(email);
      jwtLogin.setPassword("kasdjhfkadhsY776ggTyUU65khaskdjfhYuHAwjñlji");
      return tokenService.login(jwtLogin);
  }*/
}

