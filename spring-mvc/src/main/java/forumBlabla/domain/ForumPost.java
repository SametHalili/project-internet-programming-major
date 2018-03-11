package forumBlabla.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class ForumPost
{
    @Id
    @GeneratedValue
    private int postId;

    private String msg, username;
    private LocalDateTime msgTime;

    public ForumPost()
    {
    }

    public ForumPost(String msg, String username) //Default constructor
    {
        setMsg(msg);
        setUsername(username);
        setMsgTime(LocalDateTime.now());
        this.postId = 0; //Dummy value, the sql table only needs msg, username and msgTime.
    }                    //The postId will be incremented by itself in the sql table

    public ForumPost(String msg, String username, int postId) //When working in memory
    {
        setMsg(msg);
        setUsername(username);
        setMsgTime(LocalDateTime.now());
        setPostId(postId);
    }

    public ForumPost(String msg, String username, LocalDateTime localDateTime, int postId) //When you need to convert the values you get from the sql table to an object
    {
        setMsg(msg);
        setUsername(username);
        setMsgTime(localDateTime);
        setPostId(postId);
    }

    public void setMsg(String msg)
    {
        if(msg == null || msg.length() < 10 || msg.trim().length() < 10)
            throw new DomainException("ForumPost too short!");
        this.msg = msg;
    }

    public void setUsername(String username)
    {
        if(username == null || username.length() < 5 || username.trim().length() < 5)
            throw new DomainException("Username incorrect!");
        this.username = username;
    }

    public void setMsgTime(LocalDateTime msgTime)
    {
        if(msgTime == null || !(msgTime instanceof LocalDateTime))
            throw new DomainException("Something went wrong when giving the localtime!");
        this.msgTime = msgTime;
    }

    public void setPostId(int postId)
    {
        if(postId < 0)
            throw new DomainException("PostId error!");
        this.postId = postId;
    }

    public String getMsg()
    {
        return msg;
    }

    public String getUsername()
    {
        return username;
    }

    public LocalDateTime getMsgTime()
    {
        return msgTime;
    }

    public String getMsgTimeFormatted()
    {
        return msgTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public int getPostId()
    {
        return postId;
    }
}
