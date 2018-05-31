package domain.db.forumPost;

import domain.ForumPost;
import domain.Thread;
import domain.db.Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ForumPostDbJpa implements Database<ForumPost>
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

        for (ForumPost post : resultList)
        {
            forumPostMap.put(post.getPostId(), post);
        }

        em.close();
        return forumPostMap;
    }

    @Override
    public void add(ForumPost forumPost)
    {
        ForumPost newPost = new ForumPost(forumPost.getMsg(), forumPost.getUsername(), forumPost.getThreadPostedId());
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Thread thread = em.find(Thread.class, newPost.getThreadPostedId());
        thread.getForumPostList().add(forumPost);

        em.persist(forumPost);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void edit(ForumPost newPost)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        ForumPost forumPost = em.find(ForumPost.class, newPost.getPostId());

        em.getTransaction().begin();

        forumPost.setMsg(newPost.getMsg());
        Thread thread = em.find(Thread.class, newPost.getThreadPostedId());
        thread.editForumPostList(forumPost);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int postId)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        ForumPost forumPost = em.find(ForumPost.class, postId);

        em.getTransaction().begin();

        Thread thread = em.find(Thread.class, forumPost.getThreadPostedId());
        for(int i = 0; i < thread.getForumPostList().size(); i++)
        {
            if(thread.getForumPostList().get(i).getPostId() == postId)
            {
                thread.getForumPostList().remove(i);
            }
        }
        em.remove(forumPost);
        em.getTransaction().commit();
        em.close();
    }

    public int getLatestId()
    {
        int id = 0;
        EntityManager em = entityManagerFactory.createEntityManager();
        String query = "SELECT post FROM ForumPost post";
        List<ForumPost> resultList = em.createQuery(query, ForumPost.class).getResultList();
        for (ForumPost forumPost: resultList)
        {
            int currId = forumPost.getPostId();
            if(currId > id)
            {
                id = currId;
            }
        }
        em.close();

        return id;
    }

    public String getType()
    {
        return "JPA";
    }
}
