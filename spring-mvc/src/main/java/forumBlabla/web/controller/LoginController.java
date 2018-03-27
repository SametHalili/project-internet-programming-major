package forumBlabla.web.controller;

import forumBlabla.domain.ForumUser;
import forumBlabla.service.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
@RequestMapping(value="/login")
public class LoginController
{
    private final ForumUserService forumUserService;

    public LoginController(@Autowired ForumUserService forumUserService)
    {
        this.forumUserService = forumUserService;
    }

    @ModelAttribute("user")
    public ForumUser setUpUserForm()
    {
        return new ForumUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoginForm(@ModelAttribute("user") ForumUser user)
    {
        return new ModelAndView("/login", "user", user);
    }

    @RequestMapping(params = "doLogin", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("user") ForumUser user, BindingResult result)
    {
        if(result.hasErrors() ||
                (!forumUserService.getUserBool(user.getUsername())
                ||!forumUserService.getUser(user.getUsername()).getPassword()
                                                          .equals(user.getPassword())))
        {
            user.setUsername("");
            user.setPassword("");
            user.setUserId(0);
            return "login";
        }
        return "successLog";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(@ModelAttribute("user") ForumUser user)
    {
        user.setUserId(0);
        user.setUsername("");
        user.setPassword("");
        return "successLogout";
    }

    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String cancelAction()
    {
        return "redirect:/index.htm";
    }
}
