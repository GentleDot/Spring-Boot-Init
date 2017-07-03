package net.gentledot.config.web.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Created by Sang on 2017-05-04.
 */
@Configuration
public class ViewResolverConfig {

    /* thymeleaf*/
    @Bean
    public ServletContextTemplateResolver templateResolver(){
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);

        return templateResolver;
    }

    @Bean
    @Autowired
    public SpringTemplateEngine templateEngine(ServletContextTemplateResolver templateResolver){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }

    @Bean
    @Autowired
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        thymeleafViewResolver.setViewNames(new String[]{"thymeleaf/*","thymeleaf/**/*"});
        thymeleafViewResolver.setTemplateEngine(templateEngine);

        return thymeleafViewResolver;
    }

    /*JSP view Resolver*/

    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

}
