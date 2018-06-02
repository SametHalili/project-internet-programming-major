package domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Forum
{
    @Id
    @GeneratedValue
    private int forumId;

    @NotNull(message="{error.no.username}")
    @Size(min = 3, message="{invalid.no.username}")
    private String forumName;

    @NotNull(message="{error.no.username}")
    @Size(min = 10, message="{invalid.no.username}")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy KK:mm a")
    private LocalDateTime forumCreated;

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
    public LocalDateTime getForumCreated()
    {
        return forumCreated;
    }

    public void setForumCreated(LocalDateTime forumCreated)
    {
        if(!(forumCreated instanceof LocalDateTime))
            throw new DomainException("Something went wrong when giving the localtime!");

        if(forumCreated == null)
            this.forumCreated = LocalDateTime.now();
        else
            this.forumCreated = forumCreated;
    }

    @SuppressWarnings("unused")
    @JsonIgnore
    public String getForumCreatedFormatted()
    {
        return forumCreated.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
