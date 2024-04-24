package io.github.kloping.mywebsite.broadcast;

import com.sun.mail.pop3.POP3Message;
import io.github.kloping.file.FileUtils;
import io.github.kloping.judge.Judge;

import javax.mail.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import static io.github.kloping.mywebsite.utils.EmailSender.PASSWORD;
import static io.github.kloping.mywebsite.utils.EmailSender.SENDER;

/**
 * @author github.kloping
 */
public class EmailReceivesBroadcast extends Broadcast<EmailReceivesBroadcast.EmailReceivesReceiver> implements Runnable {
    public static final EmailReceivesBroadcast INSTANCE = new EmailReceivesBroadcast();
    public static final String HOST = "outlook.office365.com";
    public static final String EMAIL_ID = "./email.id";
    private String user = SENDER;
    private String password = PASSWORD;
    private String host = HOST;
    private Session session;


    public EmailReceivesBroadcast() {
        super("email broadcast");
        Properties props = new Properties();
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.auth", "true");
        props.put("mail.transport.protocol", "pop3");
        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
    }

    private final List<EmailReceivesBroadcast.EmailReceivesReceiver> RECEIVES = new ArrayList<>();

    public synchronized void broadcast(POP3Message message) {
        Iterator<EmailReceivesBroadcast.EmailReceivesReceiver> iterator = RECEIVES.iterator();
        while (iterator.hasNext()) {
            try {
                EmailReceivesBroadcast.EmailReceivesReceiver receiver = iterator.next();
                receiver.onReceive(message);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public boolean add(EmailReceivesReceiver receiver) {
        return RECEIVES.add(receiver);
    }

    @Override
    public boolean remove(EmailReceivesReceiver receiver) {
        return RECEIVES.remove(receiver);
    }

    public interface EmailReceivesReceiver extends Receiver<POP3Message> {
        @Override
        void onReceive(POP3Message message);
    }

    private Integer id;

    @Override
    public void run() {
        try {
            if (id == null) {
                String ids = FileUtils.getStringFromFile(EMAIL_ID).trim();
                if (Judge.isNotEmpty(ids)) {
                    id = Integer.valueOf(ids);
                }
            }
            Store store = session.getStore("pop3s");
            store.connect(host, user, password);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            if (id == null) {
                Message message = folder.getMessages(new int[]{folder.getMessageCount()})[0];
                id = message.getMessageNumber();
            }
            int nid = id;
            while (true) {
                Message message = null;
                try {
                    message = folder.getMessages(new int[]{nid})[0];
                } catch (MessagingException | ArrayIndexOutOfBoundsException e) {
                    id = nid - 1;
                    FileUtils.putStringInFile(id.toString(), new File(EMAIL_ID));
                    break;
                }
                int mid = message.getMessageNumber();
                if (mid > id) {
                    this.broadcast((POP3Message) message);
                }
                nid++;
            }
            folder.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
