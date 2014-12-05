package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

@Configuration
@ImportResource("classpath:/spring.xml")
@EnableAutoConfiguration
@ComponentScan
public class Application {
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
}
