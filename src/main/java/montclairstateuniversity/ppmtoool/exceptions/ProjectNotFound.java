package montclairstateuniversity.ppmtoool.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class ProjectNotFound extends RuntimeException{


    public ProjectNotFound(String message) {
        super(message);
    }
}
