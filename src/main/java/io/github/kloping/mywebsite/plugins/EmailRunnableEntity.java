package io.github.kloping.mywebsite.plugins;

import com.sun.mail.pop3.POP3Message;
import io.github.kloping.MySpringTool.annotations.Entity;
import io.github.kloping.mywebsite.MyWebSiteApplication;
import io.github.kloping.mywebsite.broadcast.EmailReceivesBroadcast;
import io.github.kloping.url.UrlUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author github.kloping
 */
@Entity
public class EmailRunnableEntity implements EmailReceivesBroadcast.EmailReceivesReceiver {

    public static final List<String> SENTABLE_NAME = new ArrayList<>();

    static {
        SENTABLE_NAME.add("HRS <notifications@github.com>");
    }

    private String url;

    private String pwd;

    public EmailRunnableEntity() {
        System.out.println(this.getClass().getName() + "=========created");
        EmailReceivesBroadcast.INSTANCE.add(this::onReceive);
    }

    @Override
    public void onReceive(POP3Message message) {
        try {
            Address address = message.getFrom()[0];
            if (SENTABLE_NAME.contains(address.toString())) {
                StringBuilder sb = new StringBuilder();
                sb.append(message.getSubject()).append("\n");
                sb.append(address.toString()).append("\n");
                Object content = message.getContent();
                if (content instanceof javax.mail.internet.MimeMultipart) {
                    MimeMultipart mimeMultipart = (MimeMultipart) content;
                    for (int index = 0; index < mimeMultipart.getCount(); index++) {
                        BodyPart bodyPart = mimeMultipart.getBodyPart(index);
                        String type = bodyPart.getContentType();
                        Object object = bodyPart.getContent();
                        if (type.contains("text/plain")) {
                            sb.append(object.toString()).append("\n");
                        } else if (type.contains("text/html")) {
                            sb.append(toPlainText(object.toString())).append("\n");
                        }
                    }
                } else {
                    sb.append(content.toString());
                }
                if (url == null) {
                    url = MyWebSiteApplication.applicationContext.getEnvironment().getProperty("auth.url").toString();
                }
                if (pwd == null) {
                    pwd = MyWebSiteApplication.applicationContext.getEnvironment().getProperty("auth.pwd").toString();
                }
                System.out.println("say =>> ");
                System.out.println(sb.toString());
                UrlUtils.getStringFromHttpUrl(url + "/say?gid=570700910&pwd=" + pwd + "&s=" + URLEncoder.encode(sb.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String toPlainText(final String html) {
        if (html == null) {
            return "";
        }
        final Document document = Jsoup.parse(html);
        final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
        document.outputSettings(outputSettings);
        document.select("br").append("\\n");
        document.select("p").prepend("\\n");
        document.select("p").append("\\n");
        final String newHtml = document.html().replaceAll("\\\\n", "\n");
        final String plainText = Jsoup.clean(newHtml, "", Safelist.none(), outputSettings);
        final String result = StringEscapeUtils.unescapeHtml3(plainText.trim());
        return result.replaceAll("[\r\n]+", "\n");
    }
}