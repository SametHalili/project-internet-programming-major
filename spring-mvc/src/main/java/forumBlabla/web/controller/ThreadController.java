package forumBlabla.web.controller;

import forumBlabla.domain.ForumPost;
import forumBlabla.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/thread")
public class ThreadController
{
    private final ForumPostService service;

    public ThreadController(@Autowired ForumPostService service)
    {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getForums()
    {
        return new ModelAndView("/thread", "thread", service.getDatabase().getAll().values());
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getNewForumPostForm()
    {
        return new ModelAndView("formPost", "forumPost", new ForumPost());
    }

    @RequestMapping(value = "/edit/{postId}", method = RequestMethod.GET)
    public ModelAndView getEditForumPostForm(@PathVariable int postId)
    {
        return new ModelAndView("editFormPost","forumPost", service.getMessage(postId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveNewForumPost(ForumPost forumPost, BindingResult result)
    {
        if(result.hasErrors())
        {
            if(service.getMessageBool(forumPost.getPostId()))
                return "editFormPost";
            return "formPost";
        }

        if(service.getMessageBool(forumPost.getPostId()))
        {
            service.editMessage(forumPost.getPostId(), forumPost.getMsg());
            return "redirect:/thread.htm";
        }

        ForumPost finalPost = new ForumPost(forumPost.getMsg(), forumPost.getUsername());
        service.addNewMessage(finalPost);
        return "redirect:/thread.htm";
    }
}