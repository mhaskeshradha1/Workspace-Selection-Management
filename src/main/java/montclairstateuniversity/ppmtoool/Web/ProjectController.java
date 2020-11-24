package montclairstateuniversity.ppmtoool.Web;


import montclairstateuniversity.ppmtoool.Services.ProjectService;
import montclairstateuniversity.ppmtoool.Services.ValidationErrorService;
import montclairstateuniversity.ppmtoool.domain.myproject;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
   @Autowired
    private ProjectService ProjectService;    //inject ProjectService class in controller class
    @Autowired
    private ValidationErrorService ValidationErrorService;   //inject ValidationErrorService class

   @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody myproject myproject, BindingResult result, Principal principal){

       ResponseEntity<?> errorMap = ValidationErrorService.ValidationErrorService(result);
       if(errorMap!=null) return errorMap;
      /* if(result.hasErrors())
       {
           //to get key value pairs use Map
           Map<String,String>errrorMap = new HashMap<>();
           for (FieldError error:result.getFieldErrors()){
               errrorMap.put(error.getField(),error.getDefaultMessage());
           }
           return new ResponseEntity<Map<String,String>>(errrorMap,HttpStatus.BAD_REQUEST);
       }*/
       //added principal login user
       myproject myproject1 = ProjectService.saveOrUpdateProject(myproject,principal.getName());
           return new ResponseEntity<myproject>(myproject, HttpStatus.CREATED);
    }
    @GetMapping("/{ProjectId}")
    public ResponseEntity<?>getProjectById(@PathVariable String ProjectId) {
        myproject myproject = ProjectService.findmyprojectByidentifier(ProjectId);
        return new ResponseEntity<myproject>(myproject, HttpStatus.OK);
    }
       @GetMapping("/all")
               public Iterable<myproject> getAllProjects(){
            return ProjectService.findAllProjects();
        }
        @DeleteMapping("/{ProjectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String ProjectId){
       ProjectService.deleteProjectByIdentifier(ProjectId);
       return new ResponseEntity<String>("Project with ID'"+ProjectId+"' was deleted",HttpStatus.OK);
        }

        }



