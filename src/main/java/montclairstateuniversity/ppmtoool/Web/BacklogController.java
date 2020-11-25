package montclairstateuniversity.ppmtoool.Web;


import montclairstateuniversity.ppmtoool.Services.ProjectTaskService;
import montclairstateuniversity.ppmtoool.Services.ValidationErrorService;
import montclairstateuniversity.ppmtoool.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private ValidationErrorService mapValidationErrorService;


    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody Task task,
                                            BindingResult result, @PathVariable String backlog_id, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.ValidationErrorService(result);
        if (errorMap != null) return errorMap;
        Task task1 = projectTaskService.addProjectTask(backlog_id, task, principal.getName());
        return new ResponseEntity<Task>(task1, HttpStatus.CREATED);
    }


    @GetMapping("/{backlog_id}")
    public Iterable<Task> getProjectBacklog(@PathVariable String backlog_id, Principal principal){
        return projectTaskService.findBacklogById(backlog_id,principal.getName());
    }


    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getTask(@PathVariable String backlog_id,@PathVariable String pt_id, Principal principal){
        Task task = projectTaskService.findPTByProjectSequence(backlog_id,pt_id, principal.getName());
        return new ResponseEntity<Task> (task, HttpStatus.OK);

    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody Task task, BindingResult result,
                                               @PathVariable String backlog_id,@PathVariable String pt_id,Principal principal){
        ResponseEntity<?> errorMap = mapValidationErrorService.ValidationErrorService(result);
        if (errorMap != null) return errorMap;

       Task updatedtask = projectTaskService.updateByProjectSequence(task,backlog_id,pt_id,principal.getName());
       return new ResponseEntity<Task>(updatedtask,HttpStatus.OK);
    }
    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?>deleteProjectTask(@PathVariable String backlog_id,@PathVariable String pt_id,Principal principal){

        projectTaskService.deletePTByProjectSequence(backlog_id, pt_id,principal.getName());

        return new ResponseEntity<String>("Project Task "+pt_id+" was deleted successfully", HttpStatus.OK);
    }
}