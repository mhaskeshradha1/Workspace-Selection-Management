package montclairstateuniversity.ppmtoool.Services;


import montclairstateuniversity.ppmtoool.domain.Backlog;
import montclairstateuniversity.ppmtoool.domain.Task;
import montclairstateuniversity.ppmtoool.repositories.BacklogRepository;
import montclairstateuniversity.ppmtoool.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private TaskRepository taskRepository;

    public Task addProjectTask(String myprojectIdentifier, Task task) {
        //child tasks added to the parent task ...considering parent (backlog)is exists.
        Backlog backlog = backlogRepository.findByMyprojectidentifier(myprojectIdentifier);
        //set the backlog (parent task) to child task.
        task.setBacklog(backlog);
        //project sequence starts from 0 and continue to increase as tasks added to the dashboard.
        Integer BacklogSequence = backlog.getPTSequence();

        //if will delete IDPRO-2(mini task), still next project/mini child task wil be IDPRO-3 not IDPRO-2.


        //update backlog task
        BacklogSequence++;
        backlog.setPTSequence(BacklogSequence);
        // follow pattern IDPRO-1,IDPRO-2,IDPRO-3..and add sequence to task
        task.setProjectSequence(backlog.getMyprojectidentifier() + "-" + BacklogSequence);
        task.setMyprojectidentifier(myprojectIdentifier);
        //set the priority to the tasks

        if (task.getStatus() == "" || task.getStatus() == null) {
            task.setStatus("TO_DO");
        }
        if (task.getPriority() == null) {
            task.setPriority(3);
        }
        return taskRepository.save(task);
    }

    public  Iterable<Task>findBacklogById(String id){
        return taskRepository.findByMyprojectidentifierOrderByPriority(id);
    }

}
    
