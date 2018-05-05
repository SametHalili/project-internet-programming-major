package forumBlabla.web.config;

import forumBlabla.domain.db.user.ForumUserDbInMemory;
import forumBlabla.domain.db.user.ForumUserDbJpa;
import forumBlabla.service.ForumPostService;
import forumBlabla.service.ForumUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig
{
    @Bean
    public ForumPostService service()
    {
        return new ForumPostService("JPA");
    }

    @Bean
    public ForumUserService userService() { return new ForumUserService(new ForumUserDbJpa()); }
}
