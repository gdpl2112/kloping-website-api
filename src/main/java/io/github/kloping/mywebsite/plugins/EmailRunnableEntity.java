package io.github.kloping.mywebsite.plugins;

import com.sun.mail.pop3.POP3Message;
import io.github.kloping.MySpringTool.annotations.Entity;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.KlopingBlogApplication;
import io.github.kloping.mywebsite.broadcast.EmailReceivesBroadcast;
import io.github.kloping.mywebsite.utils.BlogCodeUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.*;

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
                String uhref = null;
                StringBuilder sb = new StringBuilder();
                sb.append(message.getSubject()).append("\n===========\n");
                Object content = message.getContent();
                if (content instanceof javax.mail.internet.MimeMultipart) {
                    MimeMultipart mimeMultipart = (MimeMultipart) content;
                    for (int index = 0; index < mimeMultipart.getCount(); index++) {
                        BodyPart bodyPart = mimeMultipart.getBodyPart(index);
                        String type = bodyPart.getContentType();
                        Object object = bodyPart.getContent();
                            if (type.contains("text/html")) {
                                Document doc0 = Jsoup.parse(object.toString());
                                for (Element element : doc0.getElementsByTag("a")) {
                                    if (element.text().equals("view it on GitHub") || element.text().equals("release page")) {
                                        uhref = element.attr("href");
                                    }
                                }
                                Iterator<Element> iter0 = doc0.body().getElementsByTag("p").iterator();
                                while (iter0.hasNext()) {
                                    Element e0 = iter0.next();
                                    if (!iter0.hasNext()) {
                                        e0.remove();
                                    }
                                }
                                sb.append(doc0.body().wholeText().trim());
                            }
                    }
                } else {
                    sb.append(content.toString());
                }
                if (url == null) {
                    url = KlopingBlogApplication.applicationContext.getEnvironment().getProperty("auth.url").toString();
                }
                if (pwd == null) {
                    pwd = KlopingBlogApplication.applicationContext.getEnvironment().getProperty("auth.pwd").toString();
                }
                if (Judge.isNotEmpty(uhref)) {
                    sb.append("\n===========\n").append(uhref);
                }
                System.out.println("say =>> ");
                System.out.println(sb.toString());
                Map<String, String> map = new LinkedHashMap<>();
                map.put("gid", "794238572");
                map.put("pwd", pwd);
                map.put("s", sb.toString());
                BlogCodeUtils.post(url + "/say", map);
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
