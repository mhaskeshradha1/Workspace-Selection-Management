package montclairstateuniversity.ppmtoool.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNameAlreadyExistesException extends RuntimeException{

    public UserNameAlreadyExistesException(String message) {
        super(message);
    }
}
