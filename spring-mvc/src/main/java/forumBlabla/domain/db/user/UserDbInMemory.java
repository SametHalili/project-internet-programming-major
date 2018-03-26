package forumBlabla.domain.db.user;

import forumBlabla.domain.ForumPost;
import forumBlabla.domain.User;
import forumBlabla.domain.db.DbException;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserDbInMemory implements UserDb
{
    Map<String, User> userMap = new LinkedHashMap<>();
    private static int instanceCounter = 0; //ONLY NEEDED WHEN WORKING IN MEMORY

    public UserDbInMemory()
    {
        instanceCounter = 0;
        this.add(new User("admin", "admin"));
        this.add(new User("admin2", "admin2"));
    }

    @Override
    public User get(String username) {
        return userMap.get(username);
    }

    @Override
    public Map<String, User> getAll() {
        return userMap;
    }

    @Override
    public void add(User user)
    {
        if(user == null)
            throw new DbException("User invalid");
        instanceCounter++;
        user.setUserId(instanceCounter);
        userMap.put(user.getUsername(), user);
    }

    @Override
    public void edit(String username, String newPassword)
    {
        if(newPassword == null || newPassword.length() < 6)
            throw new DbException("Password invalid");
        if(!userMap.containsKey(username))
            throw new DbException("User not found");
        int userId = userMap.get(username).getUserId();
        User newUser = new User(username, newPassword, userId);
        userMap.replace(username, newUser);
    }

    @Override
    public void delete(String username) {
        userMap.remove(username);
    }

    @Override
    public String getType() {
        return "MEMORY";
    }
}
