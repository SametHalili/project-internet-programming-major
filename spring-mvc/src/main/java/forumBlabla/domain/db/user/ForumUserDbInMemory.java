package forumBlabla.domain.db.user;

import forumBlabla.domain.ForumUser;
import forumBlabla.domain.db.DbException;

import java.util.LinkedHashMap;
import java.util.Map;

public class ForumUserDbInMemory implements ForumUserDb
{
    Map<String, ForumUser> userMap = new LinkedHashMap<>();
    private static int instanceCounter = 0; //ONLY NEEDED WHEN WORKING IN MEMORY

    public ForumUserDbInMemory()
    {
        instanceCounter = 0;
        this.add(new ForumUser("admin", "admin"));
        this.add(new ForumUser("admin2", "admin2"));
    }

    @Override
    public ForumUser get(String username) {
        return userMap.get(username);
    }

    @Override
    public Map<String, ForumUser> getAll() {
        return userMap;
    }

    @Override
    public void add(ForumUser user)
    {
        if(user == null)
            throw new DbException("ForumUser invalid");
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
            throw new DbException("ForumUser not found");
        int userId = userMap.get(username).getUserId();
        ForumUser newUser = new ForumUser(username, newPassword, userId);
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
