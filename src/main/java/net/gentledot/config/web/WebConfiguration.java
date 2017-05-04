package net.gentledot.config.web;

import net.gentledot.config.web.view.ViewResolverConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Sang on 2017-05-04.
 */
/*@Configuration
@EnableWebMvc
@Import(value = ViewResolverConfig.class)
@ComponentScan(basePackages = "net.gentledot")
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets*//**").addResourceLocations("/assets/");
    }
}*/
