package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.Service;

@Configuration
public class ApplicationConfig
{
    @Bean
    public Service service()
    {
        return new Service("JPA");
    }

}
