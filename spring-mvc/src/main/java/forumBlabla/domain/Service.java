package forumBlabla.domain;
import forumBlabla.domain.db.FactoryForumPostDb;
import forumBlabla.domain.db.ForumPostDb;
import forumBlabla.domain.db.ForumPostDbInMemory;

public class Service
{
    private FactoryForumPostDb factoryDb = new FactoryForumPostDb();
    private ForumPostDb database;

    public Service() //default
    {
        this.database = new ForumPostDbInMemory();
    }

    public Service(String dbType) //Factory pattern
    {
        setTypeDb(factoryDb.getTypeDb(dbType));
    }

    public Service(ForumPostDb strategy) //Strategy pattern
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
