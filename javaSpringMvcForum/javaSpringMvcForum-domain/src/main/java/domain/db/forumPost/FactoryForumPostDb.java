package domain.db.forumPost;

import domain.db.Database;
import domain.db.DbException;

public class FactoryForumPostDb
{
    public Database getTypeDb(String dbType)
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
