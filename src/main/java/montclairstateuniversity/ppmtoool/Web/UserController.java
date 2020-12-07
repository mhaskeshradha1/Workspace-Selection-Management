package montclairstateuniversity.ppmtoool.Web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import montclairstateuniversity.ppmtoool.Services.UserService;
import montclairstateuniversity.ppmtoool.Services.ValidationErrorService;
import montclairstateuniversity.ppmtoool.domain.User;
import montclairstateuniversity.ppmtoool.payload.JWTLoginSucessReponse;
import montclairstateuniversity.ppmtoool.payload.LoginRequest;
import montclairstateuniversity.ppmtoool.security.JwtTokenProvider;
import montclairstateuniversity.ppmtoool.validator.Uservalidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static montclairstateuniversity.ppmtoool.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ValidationErrorService ValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private Uservalidator uservalidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @Operation(summary = "Login", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = ValidationErrorService.ValidationErrorService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }



    @PostMapping("/register")
    @Operation(summary = "User Registration", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        // Validate passwords match
        uservalidator.validate(user,result);

        ResponseEntity<?> errorMap = ValidationErrorService.ValidationErrorService(result);
        if (errorMap != null) return errorMap;

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

}
