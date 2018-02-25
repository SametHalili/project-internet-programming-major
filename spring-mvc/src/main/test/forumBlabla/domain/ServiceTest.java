package forumBlabla.domain;

import forumBlabla.db.DbException;
import forumBlabla.db.ForumPostDbInMemory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest
{
    private Service service = new Service();
    @Before
    public void setUp()
    {
        service = new Service();
        ForumPost forumPost = service.createMessage("Hello this is my message","test123456");
        service.addNewMessage(forumPost);
    }

    public void makeANewForumPost()
    {
        ForumPost testPost = service.createMessage("Dit is een test","test11111");
        assertEquals(testPost.getMsg(), "Dit is een test");
        assertEquals(testPost.getUsername(), "test11111");
    }

    @Test
    public void makeANewForumPostAndAddItToDatabase()
    {
        service.addNewMessage("Dit is een test","test11111");
        int recentPostId = service.getLatestPostId();
        assertEquals(service.getMessage(recentPostId).getMsg(),"Dit is een test");
        assertEquals(service.getMessage(recentPostId).getUsername(),"test11111");
    }

    @Test
    public void editMessageOfForumPost()
    {
        service.editMessage(1,"Dit message is gewijzigd!!!!!");
        assertEquals(service.getMessage(1).getMsg(),"Dit message is gewijzigd!!!!!");
    }

    @Test
    public void deleteMessageFromDatabase()
    {
        makeANewForumPost();
        service.deleteMessage(service.getLatestPostId());
        assertNull(service.getMessage(service.getLatestPostId()));
    }

    @Test
    public void createDbInMemoryUsingStrategy()
    {
        service = new Service(new ForumPostDbInMemory());
        assertEquals(service.getDatabase().getType(), "MEMORY");
    }

    @Test
    public void creatingDbInMemoryUsingFactory()
    {
        service = new Service("MEMORY");
        assertEquals(service.getDatabase().getType(),"MEMORY");
    }

    @Test(expected = DbException.class)
    public void creatingInvalidDb()
    {
        service = new Service("123456");
    }
}