package forumBlabla.domain;

class DomainException extends RuntimeException
{
    public DomainException()
    {
        super();
    }

    public DomainException(String message)
    {
        super(message);
    }

    public DomainException(Throwable exception)
    {
        super(exception);
    }

    public DomainException(String message, Throwable exception)
    {
        super(message, exception);
    }
}
