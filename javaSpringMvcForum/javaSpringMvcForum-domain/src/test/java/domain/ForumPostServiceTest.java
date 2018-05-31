package domain;

import domain.db.DbException;
import org.junit.Before;
import org.junit.Test;
import service.Service;

import static org.junit.Assert.*;

public class ForumPostServiceTest
{
    private Service forumPostService = new Service();
    @Before
    public void setUp()
    {
        forumPostService = new Service();
        Forum forum = forumPostService.createForum("Test", "Test forum for test cases!");
        forumPostService.addNewForum(forum);
        forumPostService.addNewThread("Test thread for test", "Test123", "Test123456789", 1);
        ForumPost forumPost = forumPostService.createMessage("Hello this is my message","test123456", 1);
        forumPostService.addNewMessage(forumPost);
    }

    public void makeANewForumPost()
    {
        ForumPost testPost = forumPostService.createMessage("Dit is een test","test11111", 1);
        assertEquals(testPost.getMsg(), "Dit is een test");
        assertEquals(testPost.getUsername(), "test11111");
    }

    @Test
    public void makeANewForumPostAndAddItToDatabase()
    {
        forumPostService.addNewMessage("Dit is een test","test11111", 1);
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
        forumPostService.addNewMessage("Dit is een test","test11111", 1);
        forumPostService.deleteMessage(new ForumPost("Dit is een test","test11111", 1, forumPostService.getLatestPostId()));
        assertNull(forumPostService.getMessage(forumPostService.getLatestPostId()));
    }

    @Test
    public void creatingDbInMemoryUsingFactory()
    {
        forumPostService = new Service("MEMORY");
        assertEquals(forumPostService.getForumPostDatabase().getType(),"MEMORY");
    }

    @Test(expected = DbException.class)
    public void creatingInvalidDb()
    {
        forumPostService = new Service("123456");
    }
}