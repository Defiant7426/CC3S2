package ejercicio1;

public abstract class Notification {

    private String user;
    private String message;

    public Notification(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public abstract void sendNotification();

    public String getUser() {
        return user;
    }
    public String getMessage() {
        return message;
    }
}
