package forumBlabla.domain.db.user;

import forumBlabla.domain.ForumUser;

import java.util.Map;

public interface ForumUserDb
{
    ForumUser get(String username);
    Map<String, ForumUser> getAll();
    void add(ForumUser user);
    void edit(String username, String newPassword);
    void delete(String username);
    String getType();
}
