package web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    @java.lang.Override
    protected java.lang.Class<?>[] getRootConfigClasses()
    {
        return new Class[]{ApplicationConfig.class};
    }

    @java.lang.Override
    protected java.lang.Class<?>[] getServletConfigClasses()
    {
        return new Class[]{DispatcherServletConfig.class};
    }

    @java.lang.Override
    protected java.lang.String[] getServletMappings()
    {
        return new String[]{"*.htm"};
    }
}
