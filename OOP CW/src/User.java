
public class User {
    private String username;
    private String password;
    public User(String user_name, String password){
        this.username = user_name;
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String user_name)
    {
        this.username = user_name;
    }


}
