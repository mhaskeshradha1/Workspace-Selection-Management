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
                                            BindingResult result, @PathVariable String backlog_id) {

        ResponseEntity<?> errorMap = mapValidationErrorService.ValidationErrorService(result);
        if (errorMap != null) return errorMap;
        Task task1 = projectTaskService.addProjectTask(backlog_id, task);
        return new ResponseEntity<Task>(task1, HttpStatus.CREATED);
    }


    @GetMapping("/{backlog_id}")
    public Iterable<Task> getProjectBacklog(@PathVariable String backlog_id){
        return projectTaskService.findBacklogById(backlog_id);
    }


    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getTask(@PathVariable String backlog_id,@PathVariable String pt_id){
        Task task = projectTaskService.findPTByProjectSequence(backlog_id,pt_id);
        return new ResponseEntity<Task> (task, HttpStatus.OK);

    }
}