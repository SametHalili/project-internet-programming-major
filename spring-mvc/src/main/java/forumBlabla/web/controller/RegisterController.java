package forumBlabla.web.controller;

import forumBlabla.domain.ForumUser;
import forumBlabla.service.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/register")
public class RegisterController
{
    private final ForumUserService forumUserService;

    public RegisterController(@Autowired ForumUserService forumUserService)
    {
        this.forumUserService = forumUserService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getRegisterForm()
    {
        return new ModelAndView("/register", "newUser", new ForumUser());
    }

    @RequestMapping(params = "doRegister", method = RequestMethod.POST)
    public String doRegister(ForumUser user, BindingResult result)
    {
        if(result.hasErrors())
            return "login";

        forumUserService.addUser(user);
        return "redirect:/login.htm";
    }

    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String cancelAction()
    {
        return "redirect:/index.htm";
    }

}
