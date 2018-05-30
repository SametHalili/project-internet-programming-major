package forumBlabla.domain.db.forum;

import forumBlabla.domain.Forum;
import forumBlabla.domain.db.Database;
import forumBlabla.domain.db.DbException;

import java.util.LinkedHashMap;
import java.util.Map;

public class ForumDbInMemory implements Database<Forum>
{
    private final Map<Integer, Forum> forumMap = new LinkedHashMap<>();
    private static int instanceCounter = 0; //ONLY NEEDED WHEN WORKING IN MEMORY

    public ForumDbInMemory()
    {
        instanceCounter = 0;
    }

    @Override
    public void add(Forum newForum)
    {
        if(newForum == null)
            throw new DbException("Forum invalid");
        instanceCounter++;
        newForum.setForumId(instanceCounter);
        forumMap.put(newForum.getForumId(), newForum);
    }

    @Override
    public void edit(Forum editedForum)
    {
        if((editedForum.getForumName() == null || editedForum.getDescription() == null)
                || editedForum.getForumName().trim().length() < 3
                || editedForum.getDescription().trim().length() < 8)
            throw new DbException("Incorrect name/description!!");
        if(!forumMap.containsKey(editedForum.getForumId()))
            throw new DbException("");
        forumMap.get(editedForum.getForumId()).setForumName(editedForum.getForumName());
        forumMap.get(editedForum.getForumId()).setDescription(editedForum.getDescription());
    }

    @Override
    public void delete(int forumId)
    {
        forumMap.remove(forumId);
    }

    @Override
    public Forum get(int forumId)
    {
        return forumMap.get(forumId);
    }

    @Override
    public Map<Integer, Forum> getAll()
    {
        return forumMap;
    }

    @Override
    public int getLatestId()
    {
        return instanceCounter;
    }

    @Override
    public String getType()
    {
        return "MEMORY";
    }
}
