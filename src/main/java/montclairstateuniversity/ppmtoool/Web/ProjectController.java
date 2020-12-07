package montclairstateuniversity.ppmtoool.Web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "Create project", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createNewProject(@Valid @RequestBody myproject myproject, BindingResult result, Principal principal) {

        ResponseEntity<?> errorMap = ValidationErrorService.ValidationErrorService(result);
        if (errorMap != null) return errorMap;
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
        myproject myproject1 = ProjectService.saveOrUpdateProject(myproject, principal.getName());
        return new ResponseEntity<myproject>(myproject, HttpStatus.CREATED);
    }

    @GetMapping("/{ProjectId}")
    @Operation(summary = "Get project by Id", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getProjectById(@PathVariable String ProjectId, Principal principal) {
        myproject myproject = ProjectService.findmyprojectByidentifier(ProjectId, principal.getName());
        return new ResponseEntity<myproject>(myproject, HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Get All project", security = @SecurityRequirement(name = "bearerAuth"))
    public Iterable<myproject> getAllProjects(Principal principal) {
        return ProjectService.findAllProjects(principal.getName());
    }


    @DeleteMapping("/{ProjectId}")
    @Operation(summary = "Delete project by Id", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deleteProject(@PathVariable String ProjectId, Principal principal) {
        ProjectService.deleteProjectByIdentifier(ProjectId, principal.getName());
        return new ResponseEntity<String>("Project with ID'" + ProjectId + "' was deleted", HttpStatus.OK);
    }

}



