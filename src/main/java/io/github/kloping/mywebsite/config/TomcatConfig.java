package io.github.kloping.mywebsite.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @function   http访问配置类
 *
 */
@Configuration
public class TomcatConfig {

    @Value("${http.port}")
    private int httpPort;

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                if (factory instanceof TomcatServletWebServerFactory) {
                    TomcatServletWebServerFactory webServerFactory = (TomcatServletWebServerFactory)factory;
                    Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
                    connector.setPort(httpPort);
                    webServerFactory.addAdditionalTomcatConnectors(connector);
                }
            }
        };
    }

}
