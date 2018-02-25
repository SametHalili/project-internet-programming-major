package forumBlabla.domain.db;
import forumBlabla.domain.ForumPost;
import java.util.Map;

public class ForumPostDbSql implements ForumPostDb //TODO: sql implementation
{
    @Override
    public ForumPost get(int postId)
    {
        return null;
    }

    @Override
    public Map<Integer, ForumPost> getAll()
    {
        return null;
    }

    @Override
    public void add(ForumPost forumPost)
    {

    }

    @Override
    public void edit(int postId, String newMessage)
    {

    }

    @Override
    public void delete(int postId)
    {

    }

    @Override
    public int getLatestPostId() {
        return 0;
    }

    @Override
    public String getType() {
        return "SQL";
    }
}
