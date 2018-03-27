package forumBlabla.domain.db.user;

import forumBlabla.domain.ForumUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class ForumUserDbJpa implements ForumUserDb
{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("forum");

    @Override
    public ForumUser get(String username)
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        ForumUser user = em.find(ForumUser.class, username);

        em.close();
        return user;
    }

    @Override
    public Map<String, ForumUser> getAll()
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        Map<String, ForumUser> forumPostMap = new LinkedHashMap<>();
        String query = "SELECT user FROM ForumUser user";
        List<ForumUser> resultList = em.createQuery(query, ForumUser.class).getResultList();

        for (ForumUser user: resultList)
        {
            forumPostMap.put(user.getUsername(), user);
        }

        em.close();
        return forumPostMap;
    }

    @Override
    public void add(ForumUser user)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void edit(String username, String newPassword)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        ForumUser user = em.find(ForumUser.class, username);

        em.getTransaction().begin();
        user.setUsername(username);
        user.setPassword(newPassword);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void delete(String username)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        ForumUser user = em.find(ForumUser.class, username);

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public String getType() {
        return "JPA";
    }
}
