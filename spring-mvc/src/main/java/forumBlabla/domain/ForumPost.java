package forumBlabla.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class ForumPost
{
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

    @JsonFormat(pattern = "dd-MM-yyyy KK:mm a")
    private LocalDateTime msgTime;

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
            this.msgTime = LocalDateTime.now();
        else
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

    @JsonIgnore
    public String getMsgTimeFormatted()
    {
        return msgTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
