package main;

import org.atmosphere.cpr.AtmosphereServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

@Configuration
@ImportResource(value = {"classpath:/spring.xml"})
@EnableAutoConfiguration
@ComponentScan(basePackages = {"main/*", "main/webapp/*"})
public class Application {
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    public static void main(String[] a) {
        // ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationContext context = SpringApplication.run(Application.class, a);

        System.out.println("Let us inspect the beans provided by the Spring boot ");
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

    }

    @Bean
    public ServletRegistrationBean usersRegistration()
    {
        ServletRegistrationBean registration = new ServletRegistrationBean(new AtmosphereServlet());
        registration.addUrlMappings("/users/posts/*");
        return registration;
    }

}
