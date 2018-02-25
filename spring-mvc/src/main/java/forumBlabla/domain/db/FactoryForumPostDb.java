package forumBlabla.db;

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
//            case "SQL": /* not implemented yet */
//                return new ForumPostDbSql();
            default:
                throw new DbException("Wrong dbType!");
        }
    }

}
