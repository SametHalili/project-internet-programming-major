package web.controller;

import domain.newsapi.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("SameReturnValue")
@Controller
@RequestMapping(value = {"", "/", "/index"})
public class IndexController
{
    private final Service service;

    public IndexController(@Autowired Service service)
    {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIndex()
    {
        RestTemplate restTemplate = new RestTemplate();
        Result response = restTemplate.getForObject(
                "https://domain.newsapi.org/v2/top-headlines?country=be&apiKey=b2f3fc0d68e749918cff86d0a76db876",
                Result.class);
        ModelAndView mav = new ModelAndView();
        mav.addObject("headlines", response.getArticles());
        mav.addObject("forums", service.getForums());
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginAdminPage()
    {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutAdmin(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/login.htm?logout";
    }
}
