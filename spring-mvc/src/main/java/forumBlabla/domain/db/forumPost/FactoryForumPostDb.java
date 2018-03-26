package forumBlabla.domain.db.forumPost;

import forumBlabla.domain.db.DbException;

public class FactoryForumPostDb
{
    public ForumPostDb getTypeDb(String dbType)
    {
        if(dbType == null)
            throw new DbException("Wrong dbType!");
        switch (dbType)
        {
            case "MEMORY":
                return new ForumPostDbInMemory();
            case "JPA":
                return new ForumPostDbJpa();
            default:
                throw new DbException("Wrong dbType!");
        }
    }

}
