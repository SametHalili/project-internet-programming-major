package web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/index.htm").permitAll()
                .antMatchers("/forum/newForum.htm").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/editForum.htm").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/deleteForum.htm").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/thread/{threadId}/editThread.htm").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/thread/{threadId}/deleteThread.htm").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/thread/{threadId}/edit/{postId}.htm").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/thread/{threadId}/delete/{postId}.htm").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/forum.htm").permitAll()
                .antMatchers(HttpMethod.POST,"/forum/**.htm").permitAll()
                .antMatchers("/forum/**.htm").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.htm").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic();
        http.csrf().disable();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("t").roles("ADMIN").build());
        return manager;
    }
}