package forumBlabla.web.controller;

import forumBlabla.domain.User;
import forumBlabla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/user")
public class UserController
{
    private final UserService userService;

    public UserController(@Autowired UserService userService)
    {
        this.userService = userService;
    }
    //TODO: public profiles

    @RequestMapping(value="/profile",method = RequestMethod.GET)
    public ModelAndView getPrivateProfile(@SessionAttribute("user") User user)
    {
        if(user.getUsername().isEmpty() )
            return new ModelAndView("/index");
        return new ModelAndView("userProfile", "user", user);
    }
}
