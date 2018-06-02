package domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class ForumPost
{
    @Transient
    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue
    private int postId;

    @NotNull
    private int threadPostedId;

    @NotNull(message = "{error.no.message}")
    @Size(min = 5, message = "{invalid.no.message}")
    private String msg;

    @NotNull(message="{error.no.username}")
    @Size(min = 5, message="{invalid.no.username}")
    private String username;

    private String msgTime;

    public ForumPost()
    {
    }

    public ForumPost(String msg, String username, int threadId) //Default constructor
    {
        setMsg(msg);
        setUsername(username);
        setMsgTime(LocalDateTime.now());
        setThreadPostedId(threadId);
        this.postId = 0; //Dummy value, the sql table only needs msg, username and msgTime.
    }                    //The postId will be incremented by itself in the sql table

    public ForumPost(String msg, String username, int threadId, int postId) //When working in memory
    {
        setMsg(msg);
        setUsername(username);
        setMsgTime(LocalDateTime.now());
        setThreadPostedId(threadId);
        setPostId(postId);
    }

    public ForumPost(String msg, String username, LocalDateTime localDateTime, int threadId, int postId) //When you need to convert the values you get from the sql table to an object
    {
        setMsg(msg);
        setUsername(username);
        setMsgTime(localDateTime);
        setThreadPostedId(threadId);
        setPostId(postId);
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setMsgTime(LocalDateTime msgTime)
    {
        if(!(msgTime instanceof LocalDateTime))
            throw new DomainException("Something went wrong when giving the localtime!");

        if(msgTime == null)
            this.msgTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        else
            this.msgTime = msgTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT));

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

    public String getMsgTime()
    {
        return msgTime;
    }

    public int getPostId()
    {
        return postId;
    }

    public int getThreadPostedId()
    {
        return threadPostedId;
    }

    public void setThreadPostedId(int threadPostedId)
    {
        if(threadPostedId < 0)
            throw new DomainException("threadId error!");
        this.threadPostedId = threadPostedId;
    }
}
