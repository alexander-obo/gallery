package ao.gallery.dao;

public class User {

    private static final String DEFAULT_USER_ROLE = "user";
    private String email;
    private final String login;
    private String password;
    private final String role;

    private User(String email, String login, String password, String role) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String email, String login, String password) {
        this(email, login, password, DEFAULT_USER_ROLE);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

}
