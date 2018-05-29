package forumBlabla.web.controller;

import forumBlabla.domain.Forum;
import forumBlabla.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ForumRESTController
{
    private Service service;

    public ForumRESTController(@Autowired Service service)
    {
        this.service = service;
    }

    @RequestMapping(value = "/api/forums", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Forum> getAllThreads()
    {
        return service.getForums();
    }

    //Retrieve specific thread by id with GET REQUEST

    @RequestMapping(value = "/api/forums/{forumId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Forum getThreadById(@PathVariable int forumId)
    {
        return service.getForum(forumId);
    }

    //Retrieve specific thread by id with GET REQUEST

    @RequestMapping(value = "/api/forums/{forumId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteThread(@PathVariable int forumId)
    {
        service.deleteThread(forumId);
    }

}
