package forumBlabla.web.config;

import forumBlabla.domain.db.forumPost.ForumPostDbJpa;
import forumBlabla.domain.db.user.UserDbInMemory;
import forumBlabla.domain.db.user.UserDbJpa;
import forumBlabla.service.ForumPostService;
import forumBlabla.service.UserService;
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
    public UserService userService() { return new UserService(new UserDbInMemory()); }
}
