package util;

import constant.AppConst;
import constant.ErrorMessages;
import exception.ApplicationException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MailSender {

    private String username;
    private String password;
    private Properties props;

    public MailSender(HttpServletRequest request) throws IOException {
        props = new Properties();
        props.load(new FileInputStream(
                new File(request.getServletContext().getRealPath(AppConst.MAIL_SENDER_PROPERTIES_PATH))));
        this.username = props.getProperty("mail.address");
        this.password = props.getProperty("mail.password");
    }

    public void send(String subject, String text, String toEmail) throws ApplicationException {
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException ex) {
            throw new ApplicationException(ErrorMessages.ERROR_CAN_NOT_SEND_MAIL);
        }
    }

}
