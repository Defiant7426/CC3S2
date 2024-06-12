package email;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

public class UserNotificationsTest {


    @Test
    void notifyUserSendsEmail(){

        String recipient = "luis.azana.v@uni.pe";
        String subject = "Test";
        String message = "Test message";

        // Arrange
        MailServerImpl mailServer = new MailServerImpl();
        UserNotifications userNotifications = new UserNotifications(mailServer);

        // Act
        userNotifications.notifyUser(recipient, subject, message);

        // Assert
        assertThat(mailServer.getRecipient()).isEqualTo(recipient);
        assertThat(mailServer.getSubject()).isEqualTo(subject);
        assertThat(mailServer.getMessage()).isEqualTo(message);
    }

    class MailServerImpl implements MailServer {

        private String recipient;
        private String subject;
        private String message;

        @Override
        public void sendEmail(String recipient, String subject, String message) {
            this.recipient = recipient;
            this.subject = subject;
            this.message = message;
        }

        public String getRecipient() {
            return recipient;
        }
        public String getSubject() {
            return subject;
        }
        public String getMessage() {
            return message;
        }
    }
}