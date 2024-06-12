package email;

public class UserNotifications{

    private final MailServer mailServer;

    public UserNotifications(MailServer mailServer) {
        this.mailServer = mailServer;
    }

    public void notifyUser(String recipient, String subject, String message) {
        if (recipient == null || recipient.isEmpty() || subject == null || subject.isEmpty() || message == null || message.isEmpty())
            throw new IllegalArgumentException("Los campos no pueden estar vacios");
        this.mailServer.sendEmail(recipient, subject, message);
    }
}
