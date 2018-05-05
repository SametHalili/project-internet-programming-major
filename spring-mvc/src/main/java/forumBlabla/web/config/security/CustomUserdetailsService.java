package forumBlabla.web.config.security;

import forumBlabla.domain.ForumUser;
import forumBlabla.service.ForumUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class CustomUserdetailsService implements UserDetailsService
{
    private ForumUserService service;


    public CustomUserdetailsService(ForumUserService service) {
        this.service = service;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ForumUser user = service.getUser(username);
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        authorities.add(grantedAuthority);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    /*private Set<GrantedAuthority> getAuthorities(Person person){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        LOGGER.debug("user authorities are " + authorities.toString());
        return authorities;
    }*/
}