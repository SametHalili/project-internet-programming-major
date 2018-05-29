package forumBlabla.domain.db.thread;

import forumBlabla.domain.Thread;
import forumBlabla.domain.db.Database;
import forumBlabla.domain.db.DbException;

public class FactoryThreadDb
{
    public Database<Thread> getTypeDb(String dbType)
    {
        if(dbType == null)
            throw new DbException("Wrong dbType!");
        switch (dbType)
        {
            case "MEMORY":
                return new ThreadDbInMemory();
            case "JPA":
                return new ThreadDbJpa();
            default:
                throw new DbException("Wrong dbType!");
        }
    }
}
