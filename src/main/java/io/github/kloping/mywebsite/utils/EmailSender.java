package io.github.kloping.mywebsite.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 *
 * 服务器名称: smtp.office365.com
 * 端口: 587
 * 加密方法: STARTTLS
 *
 * @author github.kloping
 */
public class EmailSender {
    public static final String SENDER = "hrs3474006766@outlook.com";
    public static final String PASSWORD = "Han_20771";
    public static final String HOST = "smtp.office365.com";
    public static final String PORT = "587";

    public static boolean sendEmail(String receiver,String title,String content) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.debug", "true");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.host", HOST);
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.port", PORT);
            props.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(props);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(SENDER));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            msg.setSubject(title);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(bodyPart);
            msg.setContent(multipart);

            Transport transport = session.getTransport();
            transport.connect(SENDER, PASSWORD);
            transport.sendMessage(msg, new Address[]{new InternetAddress(receiver)});
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
