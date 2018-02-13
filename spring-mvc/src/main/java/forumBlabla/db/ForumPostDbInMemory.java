package forumBlabla.db;

import forumBlabla.domain.ForumPost;

import java.util.*;

public class ForumPostDbInMemory implements ForumPostDb
{
    Map<Integer, ForumPost> forumPostMap = new HashMap<Integer, ForumPost>();

    public ForumPostDbInMemory()
    {
        add(new ForumPost("Hallo dit is mijn eerste post!", "Temas"));
    }

    @Override
    public ForumPost get(int postId)
    {
        return forumPostMap.get(postId);
    }

    @Override
    public Map<Integer, ForumPost> getAll()
    {
        return forumPostMap;
    }

    @Override
    public void add(ForumPost forumPost)
    {
        if(forumPost == null)
            throw new DbException("Forum post invalid!");
        forumPostMap.put(forumPost.getPostId(), forumPost);
    }

    @Override
    public void edit(ForumPost forumPost)
    {
        if(forumPost == null)
            throw new DbException("Forum post invalid!");
        if(!forumPostMap.containsKey(forumPost.getPostId()))
            throw new DbException("Form post does not exist!");
        forumPostMap.replace(forumPost.getPostId(), forumPost);
    }

    @Override
    public void delete(int postId)
    {
        forumPostMap.remove(postId);
    }
}
