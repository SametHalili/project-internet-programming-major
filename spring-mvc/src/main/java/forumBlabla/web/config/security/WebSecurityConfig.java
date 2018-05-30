package forumBlabla.web.config.security;

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
                .antMatchers("/", "index.htm").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/forum/newForum.htm", "/forum/newForum").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/editForum.htm", "/forum/{forumId}/editForum").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/deleteForum.htm", "/forum/{forumId}/deleteForum").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/thread/{threadId}/editThread.htm",
                        "/forum/{forumId}/thread/{threadId}/editThread").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/thread/{threadId}/deleteThread.htm",
                        "/forum/{forumId}/thread/{threadId}/deleteThread").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/thread/{threadId}/edit/{postId}.htm",
                        "/forum/{forumId}/thread/{threadId}/edit/{postId}").hasRole("ADMIN")
                .antMatchers("/forum/{forumId}/thread/{threadId}/delete/{postId}.htm",
                        "/forum/{forumId}/thread/{threadId}/delete/{postId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/forum").permitAll()
                .antMatchers(HttpMethod.POST,"/forum/**").permitAll()
                .antMatchers("/forum/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
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