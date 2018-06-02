package web.controller;

import domain.ForumPost;
import domain.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/forum/{forumId}/thread")
public class ThreadController
{
    private final Service service;

    public ThreadController(@Autowired Service service)
    {
        this.service = service;
    }

    @RequestMapping(value = "/{threadId}", method = RequestMethod.GET)
    public ModelAndView getThread(@PathVariable int threadId, @PathVariable int forumId)
    {
        if (service.getThread(threadId) == null
                || service.getThread(threadId).getForumPostedId() != forumId)
            throw new IllegalArgumentException("This place does not exist!");

        ModelAndView mav = new ModelAndView();
        mav.addObject("currForum", service.getForum(forumId));
        mav.addObject("currThread", service.getThread(threadId));
        mav.setViewName("thread");

        System.out.println();

        return mav;
    }

    @RequestMapping(value = "/{threadId}/new", method = RequestMethod.GET)
    public ModelAndView getNewForumPostForm(@PathVariable int threadId)
    {
        if (service.getThread(threadId) == null) throw new IllegalArgumentException("This place does not exist!");

        ForumPost temp = new ForumPost();
        temp.setThreadPostedId(threadId);

        return new ModelAndView("formPost", "newForumPost", temp);
    }

    @RequestMapping(value = "/{threadId}/edit/{postId}", method = RequestMethod.GET)
    public ModelAndView getEditForumPostForm(@PathVariable int postId, @PathVariable int threadId)
    {
        if (service.getThread(threadId) == null || service.getMessage(postId) == null
                || service.getThread(threadId).getThreadId() != threadId)
            throw new IllegalArgumentException("This place does not exist!");

        return new ModelAndView("editFormPost", "editedPost", service.getMessage(postId));
    }

    @RequestMapping(value = "/{threadId}/delete/{postId}", method = RequestMethod.GET)
    public ModelAndView getDeletePostForm(@PathVariable int postId, @PathVariable int threadId)
    {
        if (service.getThread(threadId) == null || service.getMessage(postId) == null
                || service.getThread(threadId).getThreadId() != threadId)
            throw new IllegalArgumentException("This place does not exist!");
        return new ModelAndView("deletePost", "toBeDeletedPost", service.getMessage(postId));
    }

    @RequestMapping(params = "addPost", method = RequestMethod.POST)
    public String saveNewForumPost(@ModelAttribute("newForumPost") @Valid ForumPost forumPost,
                                   BindingResult result)
    {
        int threadId = forumPost.getThreadPostedId();
        if (result.hasErrors())
            return "formPost";
        ForumPost finalPost = new ForumPost(forumPost.getMsg(), forumPost.getUsername(), threadId);
        service.addNewMessage(finalPost);
        return "redirect:/forum/" + service.getThread(finalPost.getThreadPostedId()).getForumPostedId()
                + "/thread/" + finalPost.getThreadPostedId() + ".htm";
    }


    @RequestMapping(params = "editPost", method = RequestMethod.POST)
    public String editForumPost(@ModelAttribute("editedPost") @Valid ForumPost forumPost, BindingResult result)
    {
        if (result.hasErrors())
            return "editFormPost";
        ForumPost finalPost = new ForumPost(forumPost.getMsg(), forumPost.getUsername(), forumPost.getThreadPostedId(), forumPost.getPostId());
        service.editMessage(finalPost);
        return "redirect:/forum/" + service.getThread(finalPost.getThreadPostedId()).getForumPostedId()
                + "/thread/" + finalPost.getThreadPostedId() + ".htm";
    }

    @RequestMapping(params = "deletePost", method = RequestMethod.POST)
    public String deleteForumPost(@Valid ForumPost forumPost)
    {
        int threadId = forumPost.getThreadPostedId();
        service.deleteMessage(forumPost);
        return "redirect:/forum/" + service.getThread(threadId).getForumPostedId() + "/thread/" + threadId + ".htm";
    }

    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String cancelAction()
    {
        return "redirect:/index.htm";
    }
}
