package io.github.kloping.mywebsite.plugins;

import com.sun.mail.pop3.POP3Message;
import io.github.kloping.MySpringTool.annotations.Entity;
import io.github.kloping.mywebsite.MyWebSiteApplication;
import io.github.kloping.mywebsite.broadcast.EmailReceivesBroadcast;
import io.github.kloping.mywebsite.utils.MyUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author github.kloping
 */
@Entity
public class EmailRunnableEntity implements EmailReceivesBroadcast.EmailReceivesReceiver {

    public static final List<String> SENTABLE_NAME = new ArrayList<>();

    static {
        SENTABLE_NAME.add("<notifications@github.com>");
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
            if (canSend(address)) {
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
//                        if (type.contains("text/plain")) {
//                            sb.append(object.toString()).append("\n");
//                        } else
                            if (type.contains("text/html")) {
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
                Map<String, String> map = new LinkedHashMap<>();
                map.put("gid", "794238572");
                map.put("pwd", pwd);
                map.put("s", sb.toString());
                MyUtils.post(url + "/say", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean canSend(Address address) {
        String ads = address.toString();
        for (String a : SENTABLE_NAME) {
            if (a.equals(ads)) return true;
            else if (ads.contains(a)) return true;
        }
        return false;
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
