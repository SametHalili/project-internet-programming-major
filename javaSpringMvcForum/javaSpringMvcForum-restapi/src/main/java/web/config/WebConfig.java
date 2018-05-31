package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import service.Service;

@Configuration
@EnableWebMvc
@ComponentScan("web.controller")
public class WebConfig implements WebMvcConfigurer
{
    @Bean
    public Service service()
    {
        return new Service("JPA");
    }
}