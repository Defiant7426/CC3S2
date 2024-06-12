package email;

/*
Paso 1
Definir una interfaz MailServer que abstraiga el envío de correos electrónicos
* */

public interface MailServer {
    void sendEmail(String recipient, String subject, String message);
}

