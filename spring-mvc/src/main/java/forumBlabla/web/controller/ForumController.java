package forumBlabla.web.controller;

import forumBlabla.domain.Forum;
import forumBlabla.domain.Thread;
import forumBlabla.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.ws.rs.Path;

@Controller
@RequestMapping(value = "/forum")
public class ForumController
{
    private final Service service;

    public ForumController(@Autowired Service service)
    {
        this.service = service;
    }

    @RequestMapping(value = "/{forumId}", method = RequestMethod.GET)
    public ModelAndView getThreads(@PathVariable int forumId)
    {
        if (service.getForum(forumId) == null) throw new IllegalArgumentException("This place does not exist!");

        return new ModelAndView("forumPage", "currForum", service.getForum(forumId));
    }

    @RequestMapping(value = "/newForum", method = RequestMethod.GET)
    public ModelAndView getNewForumForm()
    {
        return new ModelAndView("newForumForm", "newForum", new Forum());
    }

    @RequestMapping(value = "/{forumId}/editForum", method = RequestMethod.GET)
    public ModelAndView getEditForumForm(@PathVariable int forumId)
    {
        if (service.getForum(forumId) == null) throw new IllegalArgumentException("This place does not exist!");
        return new ModelAndView("editForum", "editedForum", service.getForum(forumId));
    }

    @RequestMapping(value = "/{forumId}/deleteForum", method = RequestMethod.GET)
    public ModelAndView getDeleteForumForm(@PathVariable int forumId)
    {
        if (service.getForum(forumId) == null) throw new IllegalArgumentException("This place does not exist!");

        return new ModelAndView("deleteForum", "toBeDeletedForum", service.getForum(forumId));
    }

    @RequestMapping(params = "createForum", method = RequestMethod.POST)
    public String saveNewForum(@Valid @ModelAttribute("newForum") Forum newForum, BindingResult newThreadError)
    {
        if (newThreadError.hasErrors())
            return "newForumForm";
        Forum finalForum = new Forum(newForum.getForumName(), newForum.getDescription());
        service.addNewForum(finalForum);
        return "redirect:/";
    }

    @RequestMapping(params = "editForumConfirmed", method = RequestMethod.POST)
    public String editForum(@Valid @ModelAttribute("editedForum") Forum newForum, BindingResult newThreadError)
    {
        if (newThreadError.hasErrors())
            return "editForum";
        service.editForum(newForum);
        return "redirect:/forum/" + newForum.getForumId() + ".htm";
    }

    @RequestMapping(params = "deleteForumConfirmed", method = RequestMethod.POST)
    public String deleteForum(@Valid @ModelAttribute("toBeDeletedForum") Forum forum, BindingResult newThreadError)
    {
        System.out.println(forum.getForumId());
        service.deleteForum(forum.getForumId());
        return "redirect:/";
    }


    @RequestMapping(value = "/{forumId}/newThread", method = RequestMethod.GET)
    public ModelAndView getNewThreadForm(@PathVariable int forumId)
    {
        if (service.getForum(forumId) == null)
            throw new IllegalArgumentException("This place does not exist!");
        Thread temp = new Thread();
        temp.setForumPostedId(forumId);
        return new ModelAndView("newThreadForm", "newThread", temp);
    }

    @RequestMapping(value = "/{forumId}/thread/{threadId}/deleteThread", method = RequestMethod.GET)
    public ModelAndView getDeleteThreadForm(@PathVariable int threadId, @PathVariable int forumId)
    {
        if (service.getThread(threadId) == null
                || service.getThread(threadId).getForumPostedId() != forumId)
            throw new IllegalArgumentException("This place does not exist!");

        return new ModelAndView("deleteThread", "toBeDeletedThread", service.getThread(threadId));
    }

    @RequestMapping(value = "/{forumId}/thread/{threadId}/editThread", method = RequestMethod.GET)
    public ModelAndView getEditThreadForm(@PathVariable int threadId, @PathVariable int forumId)
    {
        if (service.getThread(threadId) == null
                || service.getThread(threadId).getForumPostedId() != forumId)
            throw new IllegalArgumentException("This place does not exist!");
        return new ModelAndView("editThread", "editedThread", service.getThread(threadId));
    }


    @RequestMapping(params = "createThread", method = RequestMethod.POST)
    public String saveNewThread(@Valid @ModelAttribute("newThread") Thread newThread, BindingResult newThreadError)
    {
        if (newThreadError.hasErrors())
            return "newThreadForm";
        Thread newThreadFinal = new Thread(newThread.getThreadName(), newThread.getUsernameOP(), newThread.getMsgOP(), newThread.getForumPostedId());
        service.addNewThread(newThreadFinal);
        return "redirect:/forum/" + newThread.getForumPostedId() + ".htm";
    }

    @RequestMapping(params = "deleteThread", method = RequestMethod.POST)
    public String deleteThread(@ModelAttribute("toBeDeletedThread") Thread thread)
    {
        service.deleteThread(thread.getThreadId());
        return "redirect:/forum/" + thread.getForumPostedId() + ".htm";
    }

    @RequestMapping(params = "editThread", method = RequestMethod.POST)
    public String editThread(@Valid @ModelAttribute("editedThread") Thread newThread, BindingResult newThreadError)
    {
        if (newThreadError.hasErrors())
            return "editThread";
        service.editThread(newThread);
        return "redirect:/forum/" + newThread.getForumPostedId() + ".htm";
    }

    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String cancelAction()
    {
        return "redirect:/";
    }
}
