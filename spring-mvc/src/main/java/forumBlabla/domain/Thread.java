package forumBlabla.domain;

import java.util.ArrayList;
import java.util.List;

public class Thread
{
    private String threadName;
    private List<Integer> postIdList = new ArrayList<>();
    private int originalPosterId, threadId;

    public Thread(String threadName, int originalPosterId)
    {
        setThreadName(threadName);
        setOriginalPosterId(originalPosterId);
        postIdList.add(originalPosterId);
        threadId = 0;   //Dummy value, the sql table only needs msg, username and msgTime.
    }                   //The postId will be incremented by itself in the sql table

    public Thread(String threadName, int originaPosterId, int threadId)
    {
        setThreadName(threadName);
        setOriginalPosterId(originaPosterId);
        setThreadId(threadId);
    }

    public void setThreadName(String threadName)
    {
        if(threadName == null || threadName.trim().length() < 10)
            throw new DomainException("Incorrect thread name!");
        this.threadName = threadName;
    }

    public void setOriginalPosterId(int originalPosterId)
    {
        if(originalPosterId <= 0)
            throw new DomainException("Something went wrong when assigning the id");
        this.originalPosterId = originalPosterId;
    }

    public void setThreadId(int threadId)
    {
        if(threadId <= 0)
            throw new DomainException("Something went wrong when assigning the id");
        this.threadId = threadId;
    }

    public String getThreadName()
    {
        return threadName;
    }

    public int getOriginalPosterId()
    {
        return originalPosterId;
    }

    public int getThreadId()
    {
        return threadId;
    }
}
