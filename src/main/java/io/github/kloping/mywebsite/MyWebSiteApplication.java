package io.github.kloping.mywebsite;

import io.github.kloping.mywebsite.controller.UtilsController;
import io.github.kloping.url.UrlUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.github.kloping.mywebsite.Source.onCreate;

@SpringBootApplication
@Import({TomcatUtil.class})
@CrossOrigin
public class MyWebSiteApplication implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    public static void main(String[] args) {
        onCreate();
        SpringApplication.run(MyWebSiteApplication.class, args);
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(20041);
    }
}
