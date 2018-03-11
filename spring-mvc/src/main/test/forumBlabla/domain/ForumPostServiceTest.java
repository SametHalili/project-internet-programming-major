package forumBlabla.domain;

import forumBlabla.domain.db.DbException;
import forumBlabla.domain.db.forumPost.ForumPostDbInMemory;
import forumBlabla.service.ForumPostService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ForumPostServiceTest
{
    private ForumPostService forumPostService = new ForumPostService();
    @Before
    public void setUp()
    {
        forumPostService = new ForumPostService();
        ForumPost forumPost = forumPostService.createMessage("Hello this is my message","test123456");
        forumPostService.addNewMessage(forumPost);
    }

    public void makeANewForumPost()
    {
        ForumPost testPost = forumPostService.createMessage("Dit is een test","test11111");
        assertEquals(testPost.getMsg(), "Dit is een test");
        assertEquals(testPost.getUsername(), "test11111");
    }

    @Test
    public void makeANewForumPostAndAddItToDatabase()
    {
        forumPostService.addNewMessage("Dit is een test","test11111");
        int recentPostId = forumPostService.getLatestPostId();
        assertEquals(forumPostService.getMessage(recentPostId).getMsg(),"Dit is een test");
        assertEquals(forumPostService.getMessage(recentPostId).getUsername(),"test11111");
    }

    @Test
    public void editMessageOfForumPost()
    {
        forumPostService.editMessage(1,"Dit message is gewijzigd!!!!!");
        assertEquals(forumPostService.getMessage(1).getMsg(),"Dit message is gewijzigd!!!!!");
    }

    @Test
    public void deleteMessageFromDatabase()
    {
        makeANewForumPost();
        forumPostService.deleteMessage(forumPostService.getLatestPostId());
        assertNull(forumPostService.getMessage(forumPostService.getLatestPostId()));
    }

    @Test
    public void createDbInMemoryUsingStrategy()
    {
        forumPostService = new ForumPostService(new ForumPostDbInMemory());
        assertEquals(forumPostService.getDatabase().getType(), "MEMORY");
    }

    @Test
    public void creatingDbInMemoryUsingFactory()
    {
        forumPostService = new ForumPostService("MEMORY");
        assertEquals(forumPostService.getDatabase().getType(),"MEMORY");
    }

    @Test(expected = DbException.class)
    public void creatingInvalidDb()
    {
        forumPostService = new ForumPostService("123456");
    }
}