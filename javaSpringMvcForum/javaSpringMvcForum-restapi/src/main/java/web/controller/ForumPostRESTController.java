package web.controller;

import domain.ForumPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.Service;

import java.util.List;

@RestController
public class ForumPostRESTController
{
    private final Service service;

    public ForumPostRESTController(@Autowired Service service)
    {
        this.service = service;
    }

    //Retrieve all posts with GET request

    @RequestMapping(value = "/api/forumposts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ForumPost> getForumPosts()
    {
        return service.getForumPosts();
    }

    //Retrieve one specific post with GET request

    @RequestMapping(value = "/api/forumposts/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ForumPost getForumPosts(@PathVariable("id") int id)
    {
        return service.getForumPostDatabase().get(id);
    }

    //Make a new forum post with POST request

    @RequestMapping(path="/api/forumposts/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createForumPost(@RequestBody ForumPost forumPost)
    {
        service.addNewMessage(forumPost.getMsg(), forumPost.getUsername(), forumPost.getThreadPostedId());
    }

    //Edit a forum post with PUT request

    @RequestMapping(value = "/api/forumposts/{id}", method = RequestMethod.PUT)
    public void editForumPost(@PathVariable("id") int id, @RequestBody String message)
    {
        ForumPost currentForumPost = service.getMessage(id);
        currentForumPost.setMsg(message);

        service.editMessage(currentForumPost);
    }

    // Delete a forum post with DELETE request

    @RequestMapping(value = "/api/forumposts/{id}", method = RequestMethod.DELETE)
    public void deleteForumPost(@PathVariable("id") int id)
    {
        service.deleteMessage(service.getForumPostDatabase().get(id));
    }
}
