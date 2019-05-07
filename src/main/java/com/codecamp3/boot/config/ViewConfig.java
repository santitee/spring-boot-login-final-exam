package com.codecamp3.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
@Configuration
@EnableWebMvc
@ComponentScan("boot")
public class ViewConfig implements WebMvcConfigurer {

  @Autowired
  private ApplicationContext applicationContext;

  @Bean
  public SpringResourceTemplateResolver templateResolver() {
     SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
     templateResolver.setApplicationContext(applicationContext);
     templateResolver.setCacheable(false);
     templateResolver.setPrefix("classpath:/templates/");
     templateResolver.setSuffix(".html");
     return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
     SpringTemplateEngine templateEngine = new SpringTemplateEngine();
     templateEngine.setTemplateResolver(templateResolver());
     templateEngine.setEnableSpringELCompiler(true);
     return templateEngine;
  }
  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
     ThymeleafViewResolver resolver = new ThymeleafViewResolver();
     resolver.setTemplateEngine(templateEngine());
     registry.viewResolver(resolver);
  }

}
