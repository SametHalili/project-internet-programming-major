package forumBlabla.service;
import forumBlabla.domain.ForumPost;
import forumBlabla.domain.db.forumPost.FactoryForumPostDb;
import forumBlabla.domain.db.forumPost.ForumPostDb;
import forumBlabla.domain.db.forumPost.ForumPostDbInMemory;

public class ForumPostService
{
    private FactoryForumPostDb factoryDb = new FactoryForumPostDb();
    private ForumPostDb database;

    public ForumPostService() //default
    {
        this.database = new ForumPostDbInMemory();
    }

    public ForumPostService(String dbType) //Factory pattern
    {
        setTypeDb(factoryDb.getTypeDb(dbType));
    }

    public ForumPostService(ForumPostDb strategy) //Strategy pattern
    {
        setTypeDb(strategy);
    }

    public void setTypeDb(ForumPostDb strategy)
    {
        this.database = strategy;
    }

    public ForumPostDb getDatabase()
    {
        return database;
    }

    public ForumPost getMessage(int postId)
    {
        return database.get(postId);
    }

    public boolean getMessageBool(int postId)
    {
        if(database.get(postId) != null)
            return true;
        return false;
    }

    public ForumPost createMessage(String message, String username)
    {
        return new ForumPost(message, username);
    }

    public void addNewMessage(String message, String username)
    {
        database.add(createMessage(message, username));
    }

    public void addNewMessage(ForumPost forumPost)
    {
        database.add(forumPost);
    }

    public void deleteMessage(int postId)
    {
        database.delete(postId);
    }

    public void editMessage(int postId, String newMessage)
    {
        database.edit(postId, newMessage);
    }

    public int getLatestPostId()
    {
        return database.getLatestPostId();
    }
}
