package forumBlabla.web.config;

import forumBlabla.domain.db.user.UserDbInMemory;
import forumBlabla.domain.db.user.UserDbJpa;
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
        return new ForumPostService("MEMORY");
    }

    @Bean
    public ForumUserService userService() { return new ForumUserService(new UserDbInMemory()); }
}
