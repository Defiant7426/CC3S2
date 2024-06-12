package email;

public class Main {
    public static void main(String[] args) {
        //String smtpHost, int port, String username, String password
        String smtpHost = "smtp-mail.outlook.com";
        int port = 587;
        String username = "themathpro_1998@hotmail.com";
        String password = "Johitantwo002";
        MailServer mailServer = new RealMailServer(smtpHost, port, username, password);
        UserNotifications userNotifications = new UserNotifications(mailServer);
        userNotifications.notifyUser("pdelacruzv@uni.pe",
                "Mail de Prueba",
                "Este es un mensaje de prueba");
    }
}
