package montclairstateuniversity.ppmtoool.Services;


import montclairstateuniversity.ppmtoool.domain.User;
import montclairstateuniversity.ppmtoool.exceptions.UserNameAlreadyExistesException;
import montclairstateuniversity.ppmtoool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public User saveUser (User newUser){
        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UserNameAlreadyExistesException("Username '"+newUser.getUsername()+"' already exists");
        }

    }


}
