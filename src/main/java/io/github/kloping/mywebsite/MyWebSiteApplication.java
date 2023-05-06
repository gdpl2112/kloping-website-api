package io.github.kloping.mywebsite;

import io.github.kloping.common.Public;
import io.github.kloping.file.FileUtils;
import io.github.kloping.mywebsite.hangb.HangBotStarter;
import io.github.kloping.mywebsite.webhook.WebHookStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

import static io.github.kloping.mywebsite.Source.onCreate;

@SpringBootApplication
@Import({TomcatUtil.class})
@CrossOrigin
public class MyWebSiteApplication implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    static Map<String, String> map = new HashMap<>();

    static {
        map.put("accept", "*/*");
        map.put("accept-encoding", "gzip, deflate, br");
        map.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        map.put("content-type", "application/x-www-form-urlencoded; charset=utf-8");
        map.put("origin", "https://b.alipay.com");
        map.put("referer", "https://b.alipay.com/");
    }

    public static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        onCreate();
        applicationContext = SpringApplication.run(MyWebSiteApplication.class, args);
        io.github.kloping.mywebsite.plugins.Source.before();
        System.out.println("start succes -v 5-4");

        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        FileUtils.putStringInFile(pid, new File("./web.pid"));

        Public.EXECUTOR_SERVICE.submit(new WebHookStarter());
//        Public.EXECUTOR_SERVICE.submit(() -> HangBotStarter.main(args));
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(20041);
    }
}
