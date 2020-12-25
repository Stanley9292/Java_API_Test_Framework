package entities;

public class User {
    public static final String MESSAGE = "message";
    public static final String DOCUMENTATION_URL = "documentation_url";
    public static final String USERNAME = "login";
    public static final String ID = "id";

    private String login;
    private int id;

    public String getLogin(){
        return login;
    }

    public int getId(){
        return id;
    }

}
