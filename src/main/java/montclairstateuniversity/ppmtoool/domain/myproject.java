package montclairstateuniversity.ppmtoool.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class myproject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotNull
    @NotBlank
    private String myprojectnm;
    @NotBlank
    @Size(min = 4, max = 5, message = "identifier is required")
    @Column(updatable = false,unique = true)
    private String myprojectidentifier;
    @NotBlank
    private String description;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startdate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date enddate;
// to see the modifications on myproject object
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date created_at;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_at;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "myproject")
    @JsonIgnore
    private Backlog backlog;
//constructor
    public myproject(){

    }
//getter and setter for each argument do right click-generate-getter and setter
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMyprojectnm() {
        return myprojectnm;
    }

    public void setMyprojectnm(String myprojectnm) {
        this.myprojectnm = myprojectnm;
    }

    public String getMyprojectidentifier() {
        return myprojectidentifier;
    }

    public void setMyprojectidentifier(String myprojectidentifier) {
        this.myprojectidentifier = myprojectidentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }


    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
    protected void onUpdate(){
        this.updated_at = new Date();
    }
}
