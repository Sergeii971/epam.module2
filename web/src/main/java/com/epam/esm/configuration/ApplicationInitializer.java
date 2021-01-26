package com.epam.esm.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * The type ApplicationInitializer.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class ApplicationInitializer implements WebApplicationInitializer {
    
    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic dispatcher =
                container.addServlet("DispatcherServlet", new DispatcherServlet(rootContext));
        dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
