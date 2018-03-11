package forumBlabla.web.config;

import forumBlabla.service.ForumPostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig
{
    @Bean
    public ForumPostService service()
    {
        return new ForumPostService("MEMORY");
    }
}
