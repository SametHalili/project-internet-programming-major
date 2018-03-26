package forumBlabla.service;

import forumBlabla.domain.User;
import forumBlabla.domain.db.user.UserDb;
import forumBlabla.domain.db.user.UserDbInMemory;

public class UserService
{
    private UserDb userDb;

    public UserService()
    {
        this.userDb = new UserDbInMemory();
    }

    public UserService(UserDb strategy)
    {
        setTypeDb(strategy);
    }

    public void setTypeDb(UserDb strategy)
    {
        this.userDb = strategy;
    }

    public UserDb getDatabase()
    {
        return userDb;
    }

    public User getUser(String username)
    {
        return userDb.get(username);
    }

    public boolean getUserBool(String username)
    {
        if(userDb.get(username) != null)
            return true;
        return false;
    }

    public void addUser(User user)
    {
        userDb.add(user);
    }

    public void addUser(String username, String password)
    {
        userDb.add(new User(username, password));
    }

    public void deleteUser(String username)
    {
        userDb.delete(username);
    }
}
