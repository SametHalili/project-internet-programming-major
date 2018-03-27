package forumBlabla.service;

import forumBlabla.domain.ForumUser;
import forumBlabla.domain.db.user.ForumUserDb;
import forumBlabla.domain.db.user.ForumUserDbInMemory;


public class ForumUserService
{
    private ForumUserDb userDb;

    public ForumUserService()
    {
        this.userDb = new ForumUserDbInMemory();
    }

    public ForumUserService(ForumUserDb strategy)
    {
        setTypeDb(strategy);
    }

    public void setTypeDb(ForumUserDb strategy)
    {
        this.userDb = strategy;
    }

    public ForumUserDb getDatabase()
    {
        return userDb;
    }

    public ForumUser getUser(String username)
    {
        return userDb.get(username);
    }

    public boolean getUserBool(String username)
    {
        if(userDb.get(username) != null)
            return true;
        return false;
    }

    public void addUser(ForumUser user)
    {
        userDb.add(user);
    }

    public void addUser(String username, String password)
    {
        userDb.add(new ForumUser(username, password));
    }

    public void deleteUser(String username)
    {
        userDb.delete(username);
    }
}
