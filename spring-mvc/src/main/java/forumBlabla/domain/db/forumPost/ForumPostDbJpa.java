package forumBlabla.domain.db.forumPost;
import forumBlabla.domain.ForumPost;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class ForumPostDbJpa implements ForumPostDb //TODO: jpa implementation
{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("forum");


    @Override
    public ForumPost get(int postId)
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        ForumPost forumPost = em.find(ForumPost.class, postId);

        em.close();
        return forumPost;
    }

    @Override
    public Map<Integer, ForumPost> getAll()
    {
        EntityManager em = entityManagerFactory.createEntityManager();

        Map<Integer, ForumPost> forumPostMap = new LinkedHashMap<>();
        String query = "SELECT post FROM ForumPost post";
        List<ForumPost> resultList = em.createQuery(query, ForumPost.class).getResultList();

        for (ForumPost post: resultList)
        {
            forumPostMap.put(post.getPostId(), post);
        }

        em.close();
        return forumPostMap;
    }

    @Override
    public void add(ForumPost forumPost)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(forumPost);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void edit(int postId, String newMessage)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        ForumPost forumPost = em.find(ForumPost.class, postId);

        em.getTransaction().begin();
        forumPost.setMsg(newMessage);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int postId)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        ForumPost forumPost = em.find(ForumPost.class, postId);

        em.getTransaction().begin();
        em.remove(forumPost);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public int getLatestPostId() {
        return 0;
    } //TODO: return last post id

    @Override
    public String getType() {
        return "JPA";
    }
}
