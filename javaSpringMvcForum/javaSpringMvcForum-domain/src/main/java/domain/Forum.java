package domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Forum
{
    @Transient
    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue
    private int forumId;

    @NotNull(message="{error.no.forumName}")
    @Size(min = 3, message="{invalid.no.forumName}")
    private String forumName;

    @NotNull(message="{error.no.forumDescr}")
    @Size(min = 10, message="{invalid.no.forumDescr}")
    private String description;

    private String forumCreated;

    @JsonIgnore
    @OneToMany
    private List<Thread> threadList = new ArrayList<>();


    public Forum(String forumName, String description)
    {
        setForumName(forumName);
        setDescription(description);
        setForumCreated(LocalDateTime.now());
        forumId = 0;
    }

    public Forum(String forumName, String description, int forumId)
    {
        setForumName(forumName);
        setDescription(description);
        setForumCreated(LocalDateTime.now());
        setForumId(forumId);
    }

    public Forum()
    {

    }

    public int getForumId()
    {
        return forumId;
    }

    public void setForumId(int forumId)
    {
        if(forumId <= 0)
            throw new DomainException("Something went wrong when assigning the forumId!");
        this.forumId = forumId;
    }

    public String getForumName()
    {
        return forumName;
    }

    public void setForumName(String forumName)
    {
        this.forumName = forumName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @SuppressWarnings("unused")
    public String getForumCreated()
    {
        return forumCreated;
    }

    public void setForumCreated(LocalDateTime forumCreated)
    {
        if(!(forumCreated instanceof LocalDateTime))
            throw new DomainException("Something went wrong when giving the localtime!");

        if(forumCreated == null)
            this.forumCreated = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        else
            this.forumCreated = forumCreated.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public List<Thread> getThreadList()
    {
        return threadList;
    }

    @SuppressWarnings("unused")
    public void setThreadList(List<Thread> threadList)
    {
        this.threadList = threadList;
    }

    public void addNewThread(Thread newThread)
    {
        threadList.add(newThread);
    }

    @SuppressWarnings("unused")
    public void deleteThread(Thread threadDel)
    {
        for(int i = 0; i < threadList.size(); i++)
        {
            if(threadList.get(i).getThreadId() == threadDel.getThreadId())
            {
                threadList.remove(i);
                break;
            }
        }
    }

    public void deleteThread(int threadId)
    {
        for(int i = 0; i < threadList.size(); i++)
        {
            if(threadList.get(i).getThreadId() == threadId)
            {
                threadList.remove(i);
                break;
            }
        }
    }

    public void editThread(Thread editedThread)
    {
        for (Thread thread: threadList)
        {
            if(thread.getThreadId() == editedThread.getThreadId())
            {
                thread.setMsgOP(editedThread.getMsgOP());
                thread.setThreadName(editedThread.getThreadName());
            }
        }
    }
}
