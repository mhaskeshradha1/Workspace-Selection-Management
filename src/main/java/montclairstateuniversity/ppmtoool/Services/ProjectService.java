package montclairstateuniversity.ppmtoool.Services;

import montclairstateuniversity.ppmtoool.domain.Backlog;
import montclairstateuniversity.ppmtoool.domain.User;
import montclairstateuniversity.ppmtoool.domain.myproject;
import montclairstateuniversity.ppmtoool.exceptions.ProjectIdException;
import montclairstateuniversity.ppmtoool.repositories.BacklogRepository;
import montclairstateuniversity.ppmtoool.repositories.ProjectRepository;
import montclairstateuniversity.ppmtoool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
//added this service class to communicate with repositories. U can add all logic into controller class but its not recommended
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository ProjectRepository;               //injected projectRepository in service class


    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public myproject saveOrUpdateProject(myproject myproject, String username){
        try {
            User user = userRepository.findByUsername(username);
            //set one to many relationship
            myproject.setUser(user);
            //set projectleader
            myproject.setProjectLeader(user.getUsername());
            myproject.setMyprojectidentifier(myproject.getMyprojectidentifier().toUpperCase());
            if(myproject.getId() == null) {
                Backlog backlog = new Backlog();
                myproject.setBacklog(backlog);
                backlog.setMyproject(myproject);
                backlog.setMyprojectidentifier(myproject.getMyprojectidentifier().toUpperCase());
            }
            if(myproject.getId()!=null){
                myproject.setBacklog(backlogRepository.findByMyprojectidentifier(myproject.getMyprojectidentifier().toUpperCase()));
            }
            return ProjectRepository.save(myproject);
        }catch (Exception e){
            throw new ProjectIdException("Project Id '" +myproject.getMyprojectidentifier().toUpperCase()+"'Already exists");
        }

    }
    public myproject findmyprojectByidentifier(String ProjectId){
        myproject myproject = ProjectRepository.findByMyprojectidentifier(ProjectId.toUpperCase());
        if (myproject == null){
            throw new ProjectIdException("Project Id '" +ProjectId+"'does not exists");
        }
        return myproject;
    }

    public Iterable<myproject> findAllProjects(){
        return ProjectRepository.findAll();
    }
    public void deleteProjectByIdentifier(String ProjectId){
        myproject myproject = ProjectRepository.findByMyprojectidentifier(ProjectId.toUpperCase());
        if(myproject == null){
            throw new ProjectIdException("can not project with this ID'"+ProjectId+" '.is not exist");

        } ProjectRepository.delete(myproject);
    }


}
