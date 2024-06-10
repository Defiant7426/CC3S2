package ejercicio1;

public class AppNotification extends Notification{

    public AppNotification(String user, String message) {
        super(user, message);
    }

    @Override
    public void sendNotification() {
        System.out.println("Enviando notificación de app a " + getUser() + " con mensaje: " + getMessage());
    }
}
