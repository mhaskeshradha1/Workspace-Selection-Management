package montclairstateuniversity.ppmtoool.exceptions;

public class UserNameAlreadyExistsResponse {
    private String username;

    public UserNameAlreadyExistsResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
