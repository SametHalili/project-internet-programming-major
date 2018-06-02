package web.controller;

import domain.service.Service;
import domain.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThreadRESTController
{
    private final Service service;

    public ThreadRESTController(@Autowired Service service)
    {
        this.service = service;
    }

    //Retrieve all threads with GET REQUEST

    @RequestMapping(value = "/api/threads", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Thread> getAllThreads()
    {
        return service.getThreads();
    }

    //Retrieve specific thread by id with GET REQUEST

    @RequestMapping(value = "/api/threads/{threadId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Thread getThreadById(@PathVariable int threadId)
    {
        return service.getThread(threadId);
    }
}
