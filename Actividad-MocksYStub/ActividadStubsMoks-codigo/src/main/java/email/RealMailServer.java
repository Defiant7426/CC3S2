package email;

/*
Paso 5:
Implementar una clase RealMailServer que use SMTP para enviar correos en un entorno
de producción.
*/

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

import java.util.Properties;

public class RealMailServer implements MailServer{
    private final String smtpHost;
    private final int port;
    private final String username;
    private final String password;

        public RealMailServer(String smtpHost, int port, String username, String password) {
        this.smtpHost = smtpHost;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public void sendEmail(String recipient, String subject, String message) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", smtpHost);
        properties.setProperty("mail.smtp.port", String.valueOf(port));
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });

        try{
            Message msg = new jakarta.mail.internet.MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject(subject);
            msg.setText(message);

            Transport.send(msg);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
