package org.opengear.springboot.demo;

import org.opengear.springframework.boot.configdal.OpengearConfigdalConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootConfiguration
@Import(OpengearConfigdalConfiguration.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public EnvDemo envDemo() {
        return new EnvDemo();
    }

    public static class EnvDemo implements ApplicationListener<ApplicationStartedEvent> {


        @Override
        public void onApplicationEvent(ApplicationStartedEvent event) {
            ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
            System.out.println("configdal props -> " + environment.getProperty("person.name"));
        }
    }

}
