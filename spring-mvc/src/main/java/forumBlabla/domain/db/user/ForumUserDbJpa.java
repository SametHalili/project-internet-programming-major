package forumBlabla.domain.db.user;

import forumBlabla.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class ForumUserDbJpa implements ForumUserDb
{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("forum");

    @Override
    public User get(String username)
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        User user = em.find(User.class, username);

        em.close();
        return user;
    }

    @Override
    public Map<String, User> getAll()
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        Map<String, User> forumPostMap = new LinkedHashMap<>();
        String query = "SELECT user FROM User user";
        List<User> resultList = em.createQuery(query, User.class).getResultList();

        for (User user: resultList)
        {
            forumPostMap.put(user.getUsername(), user);
        }

        em.close();
        return forumPostMap;
    }

    @Override
    public void add(User user)
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
        User user = em.find(User.class, username);

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
        User user = em.find(User.class, username);

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
