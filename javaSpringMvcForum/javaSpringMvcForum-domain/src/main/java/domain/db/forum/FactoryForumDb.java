package domain.db.forum;

import domain.db.Database;
import domain.db.DbException;

public class FactoryForumDb
{
    public Database getTypeDb(String dbType)
    {
        if(dbType == null)
            throw new DbException("Wrong dbType!");
        switch (dbType)
        {
            case "MEMORY":
                return new ForumDbInMemory();
            case "JPA":
                return new ForumDbJpa();
            default:
                throw new DbException("Wrong dbType!");
        }
    }
}
