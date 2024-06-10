package ejercicio1;

public class MailNotification extends Notification {

    public MailNotification(String user, String message) {
        super(user, message);
    }

    @Override
    public void sendNotification() {
        System.out.println("Enviando email a " + getUser() + " con mensaje: " + getMessage());
    }
}
