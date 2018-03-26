package forumBlabla.web.controller;

import forumBlabla.domain.User;
import forumBlabla.service.UserService;
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
    private final UserService userService;

    public LoginController(@Autowired UserService userService)
    {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User setUpUserForm()
    {
        return new User();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoginForm(@ModelAttribute("user") User user)
    {
        return new ModelAndView("/login", "user", user);
    }

    @RequestMapping(params = "doLogin", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("user") User user, BindingResult result)
    {
        if(result.hasErrors() ||
                (!userService.getUserBool(user.getUsername())
                ||!userService.getUser(user.getUsername()).getPassword()
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
    public String doLogout(@ModelAttribute("user") User user)
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
