package forumBlabla.domain.db.user;

import forumBlabla.domain.User;

import java.util.Map;

public interface UserDb
{
    User get(String username);
    Map<String, User> getAll();
    void add(User user);
    void edit(String username, String newPassword);
    void delete(String username);
    String getType();
}
