package forumBlabla.domain.db.forumPost;

import forumBlabla.domain.ForumPost;
import forumBlabla.domain.db.DbException;

import java.util.*;

public class ForumPostDbInMemory implements ForumPostDb
{

    Map<Integer, ForumPost> forumPostMap = new LinkedHashMap<Integer, ForumPost>();
    private static int instanceCounter = 0; //ONLY NEEDED WHEN WORKING IN MEMORY

    public ForumPostDbInMemory()
    {
        instanceCounter = 0;
        this.add(new ForumPost("Hallo dit is mijn eerste post!", "Temas"));
        this.add(new ForumPost("Hallo dit is mijn tweede post!", "Temas"));
        this.add(new ForumPost("Hallo dit is mijn derde post!", "Temas"));
        this.add(new ForumPost("Hallo dit is mijn vierde post!", "Temas"));
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
    public void edit(int postId, String newMessage)
    {
        if(newMessage == null || newMessage.trim().length() < 10)
            throw new DbException("Message invalid!");
        if(!forumPostMap.containsKey(postId))
            throw new DbException("Form post does not exist!");
        String postUsername = forumPostMap.get(postId).getUsername();
        ForumPost newForumPost = new ForumPost(newMessage, postUsername, postId);
        forumPostMap.replace(postId, newForumPost);
    }

    @Override
    public void delete(int postId)
    {
        forumPostMap.remove(postId);
    }

    @Override
    public int getLatestPostId()
    {
        return instanceCounter;
    }

    @Override
    public String getType() {
        return "MEMORY";
    }
}
