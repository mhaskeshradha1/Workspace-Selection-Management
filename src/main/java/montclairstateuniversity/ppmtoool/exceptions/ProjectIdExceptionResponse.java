package montclairstateuniversity.ppmtoool.exceptions;

public class ProjectIdExceptionResponse {
//creating validation for myprojectidentifier..inpostman it looked like "myprojectidentifier": "must not be blank",so need exception to handle .
    private String myprojectidentifier;

    public ProjectIdExceptionResponse(String myprojectidentifier){
        this.myprojectidentifier = myprojectidentifier;
    }
//getter and setter methods
    public String getMyprojectidentifier() {
        return myprojectidentifier;
    }

    public void setMyprojectidentifier(String myprojectidentifier) {
        this.myprojectidentifier = myprojectidentifier;
    }
}
