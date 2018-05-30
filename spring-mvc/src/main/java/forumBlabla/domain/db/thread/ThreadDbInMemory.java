package forumBlabla.domain.db.thread;

import forumBlabla.domain.Thread;
import forumBlabla.domain.db.Database;
import forumBlabla.domain.db.DbException;

import java.util.LinkedHashMap;
import java.util.Map;

public class ThreadDbInMemory implements Database<Thread>
{
    private final Map<Integer, Thread> threadMap = new LinkedHashMap<>();
    private static int instanceCounter = 0; //ONLY NEEDED WHEN WORKING IN MEMORY

    public ThreadDbInMemory()
    {
        instanceCounter = 0;
    }

    @Override
    public Thread get(int threadId)
    {
        return threadMap.get(threadId);
    }

    @Override
    public Map<Integer, Thread> getAll()
    {
        return threadMap;
    }

    @Override
    public void add(Thread thread)
    {
        if(thread == null)
            throw new DbException("Thread invalid");
        instanceCounter++;
        thread.setThreadId(instanceCounter);
        threadMap.put(instanceCounter, thread);
    }

    @Override
    public void edit(Thread newThread)
    {
        if(newThread.getMsgOP() == null || newThread.getMsgOP().trim().length() < 8)
            throw new DbException("Message invalid!");
        if(!threadMap.containsKey(newThread.getThreadId()))
            throw new DbException("Thread does not exist!");
        threadMap.get(newThread.getThreadId()).setMsgOP(newThread.getMsgOP());
    }

    @Override
    public void delete(int threadId)
    {
        threadMap.remove(threadId);
    }

    public int getLatestId() {
        return instanceCounter;
    }

    public String getType() {
        return "MEMORY";
    }
}
