package forumBlabla.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping(value="/forum")
public class ForumController
{
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getThreads()
    {
        return new ModelAndView("forumPage","forumPage", new ArrayList<>());
    }
}
