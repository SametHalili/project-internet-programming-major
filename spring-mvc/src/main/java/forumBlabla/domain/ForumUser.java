package forumBlabla.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ForumUser
{
    @GeneratedValue
    private int userId;
    @Id
    private String username;
    private String password;

    public ForumUser()
    {
        this.userId = 0;
        this.username = "";
        this.password = "";
    }

    public ForumUser(String username, String password)
    {
        setUsername(username);
        setPassword(password);
        this.userId = 0;
    }

    public ForumUser(String username, String password, int userId)
    {
        setUsername(username);
        setPassword(password);
        setUserId(userId);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    } //TODO: password encryption

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }
}
