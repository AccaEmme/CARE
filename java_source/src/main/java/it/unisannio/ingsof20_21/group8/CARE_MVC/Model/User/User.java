package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User;

public class User {
    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    /**
     * @// TODO: 19/05/2021 verificare se la password deve essere sata nella classe */
    private String password;
}
