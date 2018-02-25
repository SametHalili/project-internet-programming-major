package forumBlabla.domain.db;

import forumBlabla.domain.ForumPost;
import java.util.Map;

public interface ForumPostDb
{
    ForumPost get(int postId);
    Map<Integer, ForumPost> getAll();
    void add(ForumPost forumPost);
    void edit(int postId, String newMessage);
    void delete(int postId);
    int getLatestPostId();
    String getType();
}
