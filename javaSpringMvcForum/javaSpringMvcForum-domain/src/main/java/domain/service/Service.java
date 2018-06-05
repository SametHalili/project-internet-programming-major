package domain.service;

import domain.Forum;
import domain.ForumPost;
import domain.Thread;
import domain.db.Database;
import domain.db.forum.FactoryForumDb;
import domain.db.forum.ForumDbInMemory;
import domain.db.forumPost.FactoryForumPostDb;
import domain.db.forumPost.ForumPostDbInMemory;
import domain.db.thread.FactoryThreadDb;
import domain.db.thread.ThreadDbInMemory;

import java.util.ArrayList;
import java.util.List;

public class Service
{
    //ForumPost
    private FactoryForumPostDb factoryDb = new FactoryForumPostDb();
    private Database<ForumPost> forumPostDatabase;

    //Thread
    private FactoryThreadDb threadDb = new FactoryThreadDb();
    private Database<Thread> threadDatabase;

    //Forum
    private FactoryForumDb forumDb = new FactoryForumDb();
    private Database<Forum> forumDatabase;

    //Constructors
    public Service()
    {
        this.forumPostDatabase = new ForumPostDbInMemory();
        this.threadDatabase = new ThreadDbInMemory();
        this.forumDatabase = new ForumDbInMemory();
    }

    public Service(String dbType) //Factory pattern
    {
        setForumPostDb(factoryDb.getTypeDb(dbType));
        setThreadDb(threadDb.getTypeDb(dbType));
        setForumDb(forumDb.getTypeDb(dbType));
    }

    private void setForumPostDb(Database strategy)
    {
        this.forumPostDatabase = strategy;
    }

    public void setThreadDb(Database strategy)
    {
        this.threadDatabase = strategy;
    }

    public void setForumDb(Database strategy)
    {
        this.forumDatabase = strategy;
    }

    /*ForumPost START*/

    public Database<ForumPost> getForumPostDatabase()
    {
        return forumPostDatabase;
    }

    public List<ForumPost> getForumPosts()
    {
        return new ArrayList<>(forumPostDatabase.getAll().values());
    }

    public ForumPost getMessage(int postId)
    {
        return forumPostDatabase.get(postId);
    }

    public boolean getMessageBool(int postId)
    {
        return forumPostDatabase.get(postId) != null;
    }

    public ForumPost createMessage(String message, String username, int threadId)
    {
        return new ForumPost(message, username, threadId);
    }

    public void addNewMessage(String message, String username, int threadId)
    {
        forumPostDatabase.add(createMessage(message, username, threadId));
        threadDatabase.get(threadId).addNewPostList(new ForumPost(message, username, threadId));
    }

    public void addNewMessage(ForumPost forumPost)
    {
        forumPostDatabase.add(forumPost);
        threadDatabase.get(forumPost.getThreadPostedId()).addNewPostList(forumPost);
    }

    public void deleteMessage(ForumPost forumPost)
    {
        forumPostDatabase.delete(forumPost.getPostId());
        threadDatabase.get(forumPost.getThreadPostedId()).deleteForumPostList(forumPost);
    }

    public void editMessage(ForumPost newMessage)
    {
        forumPostDatabase.edit(newMessage);
        threadDatabase.get(newMessage.getThreadPostedId()).editForumPostList(newMessage);
    }

    public void editMessage(int postId, String msg)
    {
        ForumPost edited = forumPostDatabase.get(postId);
        edited.setMsg(msg);
        forumPostDatabase.edit(edited);
        threadDatabase.get(edited.getThreadPostedId()).editForumPostList(edited);
    }

    public int getLatestPostId()
    {
        return forumPostDatabase.getLatestId();
    }

    /*ForumPost END*/

    /*Thread START*/

    public Database<Thread> getThreadDatabase()
    {
        return threadDatabase;
    }

    public List<Thread> getThreads()
    {
        return new ArrayList<>(threadDatabase.getAll().values());
    }

    public Thread getThread(int threadId)
    {
        if(threadDatabase.get(threadId) == null)
            throw new IllegalArgumentException("This place does not exist!");
        return threadDatabase.get(threadId);
    }

    public Thread createThread(String threadName, String usernameOP, String msgOP, int forumPostedId)
    {
        return new Thread(threadName, usernameOP, msgOP, forumPostedId);
    }

    public void addNewThread(String threadName, String usernameOP, String msgOP, int forumPostedId)
    {
        threadDatabase.add(createThread(threadName, usernameOP, msgOP, forumPostedId));
        forumDatabase.get(forumPostedId).addNewThread(createThread(threadName, usernameOP, msgOP, forumPostedId));

    }

    public void addNewThread(Thread thread)
    {
        threadDatabase.add(thread);
        forumDatabase.get(thread.getForumPostedId()).addNewThread(thread);
    }

    public void deleteThread(int threadId)
    {
        for (ForumPost post : threadDatabase.get(threadId).getForumPostList())
        {
            forumPostDatabase.delete(post.getPostId());
        }
        int forumId = threadDatabase.get(threadId).getForumPostedId();
        threadDatabase.delete(threadId);
        forumDatabase.get(forumId).deleteThread(threadId);
    }

    public void editThread(Thread newThread)
    {
        threadDatabase.edit(newThread);
        forumDatabase.get(newThread.getForumPostedId()).editThread(newThread);
    }

    public List<ForumPost> getPostsInThreadList(int threadId)
    {
        return threadDatabase.get(threadId).getForumPostList();
    }

    public int getLatestThreadId()
    {
        return threadDatabase.getLatestId();
    }

    /*Thread END*/

    /*Forum START*/

    public Database<Forum> getForumDatabase()
    {
        return forumDatabase;
    }

    public List<Forum> getForums()
    {
        return new ArrayList<>(forumDatabase.getAll().values());
    }

    public Forum getForum(int forumId)
    {
        if(forumDatabase.get(forumId) == null)
            throw new IllegalArgumentException("This place does not exist!");
        return forumDatabase.get(forumId);
    }

    public Forum createForum(String forumName, String description)
    {
        return new Forum(forumName, description);
    }

    public void addNewForum(String forumName, String description)
    {
        forumDatabase.add(new Forum(forumName, description));
    }

    public void addNewForum(Forum newForum)
    {
        forumDatabase.add(newForum);
    }

    public void deleteForum(int forumId)
    {
        for (Thread thread: this.getThreads())
        {
            if(thread.getForumPostedId() == forumId)
            {
                this.deleteThread(thread.getThreadId());
            }
        }
        forumDatabase.delete(forumId);
    }

    public void editForum(Forum forum)
    {
        forumDatabase.edit(forum);
    }

    public List<Thread> getThreadsInForumList(int forumId)
    {
        return forumDatabase.get(forumId).getThreadList();
    }

    public int getLatestForumId()
    {
        return forumDatabase.getLatestId();
    }

    /*Forum START*/
}
