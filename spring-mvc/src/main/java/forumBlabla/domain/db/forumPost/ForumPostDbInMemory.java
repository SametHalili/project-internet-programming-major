package forumBlabla.domain.db.forumPost;

import forumBlabla.domain.ForumPost;
import forumBlabla.domain.db.Database;
import forumBlabla.domain.db.DbException;

import java.util.*;

public class ForumPostDbInMemory implements Database<ForumPost>
{

    private final Map<Integer, ForumPost> forumPostMap = new LinkedHashMap<>();
    private static int instanceCounter = 0; //ONLY NEEDED WHEN WORKING IN MEMORY

    public ForumPostDbInMemory()
    {
        instanceCounter = 0;
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
        instanceCounter++;
        forumPost.setPostId(instanceCounter);
        forumPostMap.put(instanceCounter, forumPost);
    }

    @Override
    public void edit(ForumPost newMessage)
    {
        if(newMessage.getMsg() == null || newMessage.getMsg().trim().length() < 10)
            throw new DbException("Message invalid!");
        if(!forumPostMap.containsKey(newMessage.getPostId()))
            throw new DbException("Form post does not exist!");
        String postUsername = newMessage.getUsername();
        ForumPost newForumPost = new ForumPost(newMessage.getMsg(), postUsername, newMessage.getThreadPostedId(), newMessage.getPostId());
        forumPostMap.replace(newMessage.getPostId(), newForumPost);
    }

    @Override
    public void delete(int postId)
    {
        forumPostMap.remove(postId);
    }

    public int getLatestId()
    {
        return instanceCounter;
    }

    public String getType() {
        return "MEMORY";
    }
}
