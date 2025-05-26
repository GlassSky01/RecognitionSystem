package ciallo.glasssky.model;

public class User {
    public Object role;
    public String username;
    public String password;

    public User(Object role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }
}
