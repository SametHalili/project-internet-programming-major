package forumBlabla.web.controller;

import forumBlabla.domain.ForumUser;
import forumBlabla.service.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/user")
public class UserController
{
    private final ForumUserService forumUserService;

    public UserController(@Autowired ForumUserService forumUserService)
    {
        this.forumUserService = forumUserService;
    }
    //TODO: public profiles

    @RequestMapping(value="/profile",method = RequestMethod.GET)
    public ModelAndView getPrivateProfile()
    {
        return new ModelAndView("userProfile");
    }
}
