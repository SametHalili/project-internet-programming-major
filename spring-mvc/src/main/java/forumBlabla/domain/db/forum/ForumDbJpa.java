package forumBlabla.domain.db.forum;

import forumBlabla.domain.Forum;
import forumBlabla.domain.db.Database;
import forumBlabla.domain.db.DbException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ForumDbJpa implements Database<Forum>
{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("forum");

    @Override
    public Forum get(int forumId)
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        Forum forum = em.find(Forum.class, forumId);
        System.out.println(forum.getForumId());
        if(forum.getForumName().isEmpty())
        {
            throw new DbException("Thread does not exist");
        }

        em.close();
        return forum;
    }

    @Override
    public Map<Integer, Forum> getAll()
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        Map<Integer, Forum> forumMap = new LinkedHashMap<>();
        String query = "SELECT forum FROM Forum forum";
        List<Forum> resultList = em.createQuery(query, Forum.class).getResultList();

        for (Forum forum : resultList)
        {
            forumMap.put(forum.getForumId(), forum);
        }

        em.close();
        return forumMap;
    }

    @Override
    public void add(Forum forum)
    {
        Forum newForum = new Forum(forum.getForumName(), forum.getDescription());
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(newForum);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void edit(Forum editedForum)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Forum newForum = em.find(Forum.class, editedForum.getForumId());
        em.getTransaction().begin();

        newForum.setForumName(editedForum.getForumName());
        newForum.setDescription(editedForum.getDescription());

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Forum forum = em.find(Forum.class, id);

        em.getTransaction().begin();
        System.out.println(forum.getForumId());



        em.remove(forum);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public int getLatestId()
    {
        int id = 0;
        EntityManager em = entityManagerFactory.createEntityManager();
        String query = "SELECT forum FROM Forum forum";
        List<Forum> resultList = em.createQuery(query, Forum.class).getResultList();

        for (Forum forum: resultList)
        {
            int currId = forum.getForumId();
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
