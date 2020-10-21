package montclairstateuniversity.ppmtoool.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationErrorService {
    public ResponseEntity<?> ValidationErrorService(BindingResult result) {
        //below if block and for block shifted from controller class to this class
        if (result.hasErrors())
        {
            //to get key value pairs use Map
            Map<String, String> errrorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors())
            {
                errrorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errrorMap, HttpStatus.BAD_REQUEST);
        }
            return null;
        }
    }

