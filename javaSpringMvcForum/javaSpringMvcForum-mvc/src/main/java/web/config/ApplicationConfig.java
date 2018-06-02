package web.config;

import domain.service.Service;
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
