package forumBlabla.web.config;

import forumBlabla.service.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig
{
    @Bean
    public Service service()
    {
        return new Service("JPA");
    }
}
