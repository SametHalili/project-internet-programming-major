package forumBlabla.web.controller;

import forumBlabla.domain.newsapi.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/", "/index"})
public class IndexController
{
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTop3Headlines()
    {
        RestTemplate restTemplate = new RestTemplate();
        Result response = restTemplate.getForObject(
                "https://newsapi.org/v2/top-headlines?country=be&apiKey=b2f3fc0d68e749918cff86d0a76db876",
                Result.class);
        return new ModelAndView("index","headlines", response.getArticles());
    }
}
