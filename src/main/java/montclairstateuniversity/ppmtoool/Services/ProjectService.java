package montclairstateuniversity.ppmtoool.Services;

import montclairstateuniversity.ppmtoool.domain.myproject;
import montclairstateuniversity.ppmtoool.exceptions.ProjectIdException;
import montclairstateuniversity.ppmtoool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
//added this service class to communicate with repositories. U can add all logic into controller class but its not recommended
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository ProjectRepository;               //injected projectRepository in service class

    public myproject saveOrUpdateProject(myproject myproject){
        try {
            myproject.setMyprojectidentifier(myproject.getMyprojectidentifier().toUpperCase());
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
