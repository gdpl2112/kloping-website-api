package io.github.kloping.mywebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;

import static io.github.kloping.mywebsite.Source.onCreate;

@SpringBootApplication
@Import({TomcatUtil.class})
@CrossOrigin
public class MyWebSiteApplication implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    public static void main(String[] args) {
        onCreate();
        SpringApplication.run(MyWebSiteApplication.class, args);
        System.out.println("start succes -v 7-23");
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(20041);
    }
}
