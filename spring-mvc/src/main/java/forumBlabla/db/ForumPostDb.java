package forumBlabla.db;

import forumBlabla.domain.ForumPost;

import java.util.Map;

public interface ForumPostDb
{
    ForumPost get(int postId);
    Map<Integer, ForumPost> getAll();
    void add(ForumPost forumPost);
    void edit(ForumPost newForumPost);
    void delete(int postId);
}
