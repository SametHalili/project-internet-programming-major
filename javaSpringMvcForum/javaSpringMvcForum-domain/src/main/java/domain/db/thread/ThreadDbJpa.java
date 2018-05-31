package domain.db.thread;

import domain.Forum;
import domain.Thread;
import domain.db.Database;
import domain.db.DbException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ThreadDbJpa implements Database<Thread>
{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("forum");


    @Override
    public Thread get(int threadId)
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        Thread thread = em.find(Thread.class, threadId);

        if(thread.getThreadName().isEmpty())
        {
            throw new DbException("Thread does not exist");
        }

        em.close();
        return thread;
    }

    @Override
    public Map<Integer, Thread> getAll()
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        Map<Integer, Thread> threadMap = new LinkedHashMap<>();
        String query = "SELECT thread FROM Thread thread";
        List<Thread> resultList = em.createQuery(query, Thread.class).getResultList();

        for (Thread thread : resultList)
        {
            threadMap.put(thread.getThreadId(), thread);
        }

        em.close();
        return threadMap;
    }

    @Override
    public void add(Thread thread)
    {
        Thread newThread = new Thread(thread.getThreadName(), thread.getUsernameOP(), thread.getMsgOP(), thread.getForumPostedId());
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Forum forum = em.find(Forum.class, thread.getForumPostedId());
        forum.addNewThread(newThread);

        em.persist(newThread);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void edit(Thread thread)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Thread editThread = em.find(Thread.class, thread.getThreadId());

        em.getTransaction().begin();

        editThread.setMsgOP(thread.getMsgOP());
        editThread.setThreadName(thread.getThreadName());

        Forum forum = em.find(Forum.class, thread.getForumPostedId());
        forum.editThread(editThread);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Thread thread = em.find(Thread.class, id);

        em.getTransaction().begin();

        Forum forum = em.find(Forum.class, thread.getForumPostedId());

        for(int i = 0; i < forum.getThreadList().size(); i++)
        {
            if(forum.getThreadList().get(i).getThreadId() == id)
            {
                forum.getThreadList().remove(i);
            }
        }

        em.remove(thread);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public int getLatestId()
    {
        int id = 0;
        EntityManager em = entityManagerFactory.createEntityManager();
        String query = "SELECT thread FROM Thread thread";
        List<Thread> resultList = em.createQuery(query, Thread.class).getResultList();
        for (Thread thread: resultList)
        {
            int currId = thread.getThreadId();
            if(currId > id)
            {
                id = currId;
            }
        }
        em.close();

        return id;
    }

    @Override
    public String getType()
    {
        return "JPA";
    }
}
