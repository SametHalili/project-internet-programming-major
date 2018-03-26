package forumBlabla.web.controller;

import forumBlabla.domain.User;
import forumBlabla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/register")
public class RegisterController
{
    private final UserService userService;

    public RegisterController(@Autowired UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getRegisterForm()
    {
        return new ModelAndView("/register", "newUser", new User());
    }

    @RequestMapping(params = "doRegister", method = RequestMethod.POST)
    public String doRegister(User user, BindingResult result)
    {
        if(result.hasErrors())
            return "login";

        userService.addUser(user);
        return "redirect:/login.htm";
    }

    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String cancelAction()
    {
        return "redirect:/index.htm";
    }

}
