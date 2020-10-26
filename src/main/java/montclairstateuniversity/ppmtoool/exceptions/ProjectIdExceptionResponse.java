package montclairstateuniversity.ppmtoool.exceptions;

public class ProjectIdExceptionResponse {
//creating validation for myprojectidentifier..
    private String myprojectidentifier;

    public ProjectIdExceptionResponse(String myprojectidentifier){
        this.myprojectidentifier = myprojectidentifier;
    }

    public String getMyprojectidentifier() {
        return myprojectidentifier;
    }

    public void setMyprojectidentifier(String myprojectidentifier) {
        this.myprojectidentifier = myprojectidentifier;
    }
}
