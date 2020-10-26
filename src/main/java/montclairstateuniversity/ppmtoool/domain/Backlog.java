package montclairstateuniversity.ppmtoool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer PTSequence = 0;

    private String myprojectidentifier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="myproj_id", nullable = false)
    @JsonIgnore
    private myproject myproject;


//one to many relationship with Task
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "backlog")
    private List<Task> Tasks = new ArrayList<>();

    public String getMyprojectidentifier() {
        return myprojectidentifier;
    }

    public void setMyprojectidentifier(String myprojectidentifier) {
        this.myprojectidentifier = myprojectidentifier;
    }

    public montclairstateuniversity.ppmtoool.domain.myproject getMyproject() {
        return myproject;
    }

    public void setMyproject(montclairstateuniversity.ppmtoool.domain.myproject myproject) {
        this.myproject = myproject;
    }

    public Backlog() {

    }
        public Long getId(){
            return id;

    }



    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }


    public List<Task> getTasks() {
        return Tasks;
    }

    public void setTasks(List<Task> tasks) {
        Tasks = tasks;
    }


}
