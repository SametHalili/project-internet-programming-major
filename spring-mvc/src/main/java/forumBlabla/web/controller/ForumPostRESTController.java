package forumBlabla.web.controller;

import forumBlabla.domain.ForumPost;
import forumBlabla.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/posts")
public class ForumPostRESTController
{
    private final ForumPostService service;

    public ForumPostRESTController(@Autowired ForumPostService service)
    {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ForumPost> getForumPosts()
    {
        return service.getForumPosts();
    }
}
