package montclairstateuniversity.ppmtoool.Services;


import montclairstateuniversity.ppmtoool.domain.Backlog;
import montclairstateuniversity.ppmtoool.domain.Task;
import montclairstateuniversity.ppmtoool.domain.myproject;
import montclairstateuniversity.ppmtoool.exceptions.ProjectNotFound;
import montclairstateuniversity.ppmtoool.repositories.BacklogRepository;
import montclairstateuniversity.ppmtoool.repositories.ProjectRepository;
import montclairstateuniversity.ppmtoool.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public Task addProjectTask(String myprojectIdentifier, Task task) {

        try {
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
        }catch (Exception e){
            throw new ProjectNotFound("ProjectNot Found");
        }

    }

    public  Iterable<Task>findBacklogById(String id){
        myproject myproject = projectRepository.findByMyprojectidentifier(id);
        if(myproject==null){
            throw new ProjectNotFound("Projectid:'"+id+"'does not exists");
        }
        return taskRepository.findByMyprojectidentifierOrderByPriority(id);
    }

    public Task findPTByProjectSequence(String backlog_id,String pt_id){
//make sure that will search on existing backlog
        Backlog backlog = backlogRepository.findByMyprojectidentifier(backlog_id);
        if(backlog==null){
            throw new ProjectNotFound("Project with ID:'"+backlog_id+"'does not exists");
        }
        //make sure that the task exists..
        Task task = taskRepository.findByProjectSequence(pt_id);
        if(task == null){
            throw new ProjectNotFound("the Task '"+pt_id+"'not found");
        }

//make sure that backlog/task id in the path corresponds to the right task
        if(!task.getMyprojectidentifier().equals(backlog_id)){
            throw new ProjectNotFound("Child Task is'"+pt_id+"'not present in parent task'"+backlog_id);
        }


        return task;
    }



    public Task updateByProjectSequence(Task updatedTask, String backlog_id, String pt_id){
        Task task = taskRepository.findByProjectSequence(pt_id);
        task= updatedTask;
        return taskRepository.save(task);
    }


}
    
