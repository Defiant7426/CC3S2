package ejercicio1;

public class Main {
    public static void main(String[] args) {
        NotificationManager notificationManager = new NotificationManager("Pedro", "Su registro es exitoso");
        notificationManager.sendNotification("email");
        notificationManager.sendNotification("sms");
        notificationManager.sendNotification("app");
    }
}