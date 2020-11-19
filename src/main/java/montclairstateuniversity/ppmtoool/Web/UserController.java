package montclairstateuniversity.ppmtoool.Web;


import montclairstateuniversity.ppmtoool.Services.UserService;
import montclairstateuniversity.ppmtoool.Services.ValidationErrorService;
import montclairstateuniversity.ppmtoool.domain.User;
import montclairstateuniversity.ppmtoool.validator.Uservalidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ValidationErrorService ValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private Uservalidator uservalidator;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        // Validate passwords match
        uservalidator.validate(user,result);

        ResponseEntity<?> errorMap = ValidationErrorService.ValidationErrorService(result);
        if (errorMap != null) return errorMap;

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

}
