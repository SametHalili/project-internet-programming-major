package forumBlabla.web.controller;

import forumBlabla.domain.ForumPost;
import forumBlabla.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ForumPostRESTController
{
    private final ForumPostService service;

    public ForumPostRESTController(@Autowired ForumPostService service)
    {
        this.service = service;
    }

    //Retrieve all posts with GET request

    @RequestMapping(value = "/rest/forumposts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ForumPost> getForumPosts()
    {
        return service.getForumPosts();
    }

    //Retrieve one specific post with GET request

    @RequestMapping(value = "/rest/forumposts/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ForumPost getForumPosts(@PathVariable("id") int id)
    {
        return service.getMessage(id);
    }

    //Make a new forum post with POST request

    @RequestMapping(path="/rest/forumposts/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createForumPost(@RequestBody ForumPost forumPost)
    {
        service.addNewMessage(forumPost.getMsg(), forumPost.getUsername());
    }

    //Edit a forum post with PUT request

    @RequestMapping(value = "/rest/forumposts/{id}", method = RequestMethod.PUT)
    public void createForumPost(@PathVariable("id") int id, @RequestBody String message)
    {
        ForumPost currentForumPost = service.getMessage(id);
        currentForumPost.setMsg(message);

        service.getDatabase().edit(id, currentForumPost.getMsg());
    }

    // Delete a forum post with DELETE request

    @RequestMapping(value = "/rest/forumposts/{id}", method = RequestMethod.DELETE)
    public void createForumPost(@PathVariable("id") int id)
    {
        service.deleteMessage(id);
    }
}
