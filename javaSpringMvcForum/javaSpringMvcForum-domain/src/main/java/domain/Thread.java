package domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Thread
{
    @Transient
    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue
    private int threadId;

    @NotNull
    private int forumPostedId;

    @NotNull(message="{error.no.threadName}")
    @Size(min = 10, message="{invalid.no.threadName}")
    private String threadName;

    @NotNull(message = "{error.no.message}")
    @Size(min = 8, message = "{invalid.no.message}")
    private String msgOP;

    @NotNull(message="{error.no.username}")
    @Size(min = 5, message="{invalid.no.username}")
    private String usernameOP;

    @NotNull
    private String threadCreated;

    @JsonIgnore
    @OneToMany
    private List<ForumPost> forumPostList = new ArrayList<>();


    public Thread(String threadName, String usernameOP, String msgOP, int forumPostedId)
    {
        setForumPostedId(forumPostedId);
        setThreadName(threadName);
        setUsernameOP(usernameOP);
        setMsgOP(msgOP);
        setThreadCreated(LocalDateTime.now());
        threadId = 0;   //Dummy value, the sql table only needs msgOP, usernameOP and threadCreatedTime.
    }                   //The threadId will be incremented by itself in the sql table

    public Thread(String threadName, String usernameOP, String msgOP, int forumPostedId, int threadId)
    {
        setForumPostedId(forumPostedId);
        setThreadName(threadName);
        setThreadId(threadId);
        setUsernameOP(usernameOP);
        setMsgOP(msgOP);
        setThreadCreated(LocalDateTime.now());
    }

    public Thread()
    {

    }

    public void addNewPostList(ForumPost newPost)
    {
        forumPostList.add(newPost);
    }

    public void setThreadName(String threadName)
    {
        this.threadName = threadName;
    }

    public void setThreadId(int threadId)
    {
        if (threadId <= 0)
            throw new DomainException("Something went wrong when assigning the id");
        this.threadId = threadId;
    }

    public String getThreadName()
    {
        return threadName;
    }

    public int getThreadId()
    {
        return threadId;
    }

    public List<ForumPost> getForumPostList()
    {
        return forumPostList;
    }

    public void setForumPostList(List<ForumPost> forumPostList)
    {
        this.forumPostList = forumPostList;
    }

    public String getMsgOP()
    {
        return msgOP;
    }

    public void setMsgOP(String msgOP)
    {
        this.msgOP = msgOP;
    }

    public String getUsernameOP()
    {
        return usernameOP;
    }

    public void setUsernameOP(String usernameOP)
    {
        this.usernameOP = usernameOP;
    }

    public String getThreadCreated()
    {
        return threadCreated;
    }

    public void setThreadCreated(LocalDateTime threadCreated)
    {
        if(!(threadCreated instanceof LocalDateTime))
            throw new DomainException("Something went wrong when giving the localtime!");

        if(threadCreated == null)
            this.threadCreated = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        else
            this.threadCreated = threadCreated.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public void deleteForumPostList(ForumPost forumPost) // bad name, actually just deletes one forumpost from list
    {
        for(int i = 0; i < forumPostList.size(); i++)
        {
            if(forumPostList.get(i).getPostId() == forumPost.getPostId())
            {
                forumPostList.remove(i);
                break;
            }
        }
    }

    public void editForumPostList(ForumPost forumPost) // bad name, actually just edits one forumpost from list
    {
        for (ForumPost post: forumPostList)
        {
            if(post.getPostId() == forumPost.getPostId())
            {
                post.setMsg(forumPost.getMsg());
                break;
            }
        }
    }

    public int getForumPostedId()
    {
        return forumPostedId;
    }

    public void setForumPostedId(int forumPostedId)
    {
        if(forumPostedId < 0)
            throw new DomainException("Something went wrong when setting the forum posted id!");
        this.forumPostedId = forumPostedId;
    }
}
