package forumBlabla.domain;

import java.time.LocalDateTime;

public class ForumPost
{
    private int postId;
    private static int instanceCounter = 0;
    private String msg, username;
    private LocalDateTime msgTime;

    public ForumPost(String msg, String username)
    {
        setMsg(msg);
        setUsername(username);
        setMsgTime(LocalDateTime.now());
        this.postId = instanceCounter;
        instanceCounter++;
    }

    private void setMsg(String msg)
    {
        if(msg == null || msg.length() < 10 || msg.trim().length() < 10)
            throw new DomainException("ForumPost too short!");
        this.msg = msg;
    }

    private void setUsername(String username)
    {
        if(username == null || username.length() < 5 || username.trim().length() < 5)
            throw new DomainException("Username incorrect!");
        this.username = username;
    }

    private void setMsgTime(LocalDateTime msgTime)
    {
        if(msgTime == null)
            throw new DomainException("Something went wrong when giving the localtime!");
        this.msgTime = msgTime;
    }

    public String getMsg()
    {
        return msg;
    }

    public String getUsername()
    {
        return username;
    }

    public LocalDateTime getMsgTime()
    {
        return msgTime;
    }

    public int getPostId()
    {
        return postId;
    }
}
