package forumBlabla.web.config;

import forumBlabla.web.config.security.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    @java.lang.Override
    protected java.lang.Class<?>[] getRootConfigClasses()
    {
        return new Class[]{ApplicationConfig.class,
                           WebSecurityConfig.class};
    }

    @java.lang.Override
    protected java.lang.Class<?>[] getServletConfigClasses()
    {
        return new Class[]{DispatcherServletConfig.class};
    }

    @java.lang.Override
    protected java.lang.String[] getServletMappings()
    {
        return new String[]{"/"};
    }
}
