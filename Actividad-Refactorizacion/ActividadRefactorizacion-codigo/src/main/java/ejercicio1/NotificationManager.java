package ejercicio1;

import java.util.Map;

public class NotificationManager {
    private String user;
    private String message;
    private Notification notification;
    private Map<String, Notification> notifications;

    public NotificationManager(String user, String message) {
        this.user = user;
        this.message = message;
        this.notifications = Map.of(
            "email", new MailNotification(this.user, this.message),
            "sms", new SmsNotification(this.user, this.message),
            "app", new AppNotification(this.user, this.message)
        );
    }

    public void sendNotification(String type) {
        notification = notifications.get(type);
        if (notification == null) {
            throw new IllegalArgumentException("Invalid notification type");
        }
        notification.sendNotification();
    }
}
