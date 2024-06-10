package ejercicio1;

public class SmsNotification extends Notification {

    public SmsNotification(String user, String message) {
        super(user, message);
    }

    @Override
    public void sendNotification() {
        System.out.println("Enviando sms a " + getUser() + " con mensaje: " + getMessage());
    }

}
