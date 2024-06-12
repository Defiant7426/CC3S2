# Actividad Mocks y Stubs

# Simulador de lanzamiento de dados

Alumno: De la Cruz Valdiviezo, Pedro Luis David

Se necesita una aplicación en Java que simule el lanzamiento de dados. Debe mostrar el resultado de lanzar un dado de seis caras aleatoriamente y permitir el cambio de la fuente de números aleatorios, facilitando las pruebas y adaptación a diferentes entornos.

Requisitos funcionales:

1. Lanzamiento de dados: La aplicación debe permitir lanzar un dado de seis caras y devolver el
resultado.
2. Flexibilidad en la generación de números aleatorios: Debe ser posible cambiar la
implementación de cómo se generan los números aleatorios sin afectar el resto del código.
3. Facilidad de pruebas: La arquitectura debe permitir la inyección de un generador de números
pseudoaleatorios o fijos para facilitar las pruebas.

Requisitos No funcionales:

1. Extensibilidad: El sistema debe estar diseñado para permitir la fácil incorporación de diferentes
tipos de dados o juegos que involucren lanzamientos de dados en el futuro.
2. Mantenibilidad: El código debe ser fácil de mantener y entender, aplicando principios SOLID y
patrones de diseño apropiados.

## Paso 1

Crear una interfaz NumerosAleatorios que defina un método para obtener números
aleatorios dentro de un rango específico.

```java
package dados;
// Paso 1: Crear una interfaz dados.NumerosAleatorios que defina un método para obtener números
//aleatorios dentro de un rango específico.

public interface NumerosAleatorios {
    int nextInt(int upperBoundExclusive);
}
```

Explicación:

Creamos un código de nombre NumerosAleatorios donde definiremos un método llamado nextInt. Este método permitirá obtener números aleatorios dentro de un rango específico, lo que será esencial para simular el lanzamiento de los dados. De esta forma, podemos asegurar la flexibilidad en la generación de números aleatorios.

## Paso 2

Modificar la clase LanzamientoDados para que utilice la interfaz NumerosAleatorios,
inyectando la dependencia a través del constructor.

```java
package dados;

// Paso 2: Modificar la clase LanzamientoDados para que utilice la interfaz NumerosAleatorios,
// inyectando la dependencia a través del constructor.

public class LanzamientoDados {
    private final int NUMERO_LADOS = 6;
    private final NumerosAleatorios rnd;

    public LanzamientoDados(NumerosAleatorios numerosAleatorios) {
        this.rnd = numerosAleatorios;
    }

    public int lanzar() {
        return rnd.nextInt(NUMERO_LADOS) + 1;
    }
}

```

Explicación:

En la clase `LanzamientoDados`, utilizamos la interfaz `NumerosAleatorios` que acabamos de crear. La dependencia de `NumerosAleatorios` se inyecta a través del constructor de `LanzamientoDados`, lo que nos permite cambiar la implementación de cómo se generan los números aleatorios sin afectar el resto del código. El método `lanzar` utiliza el método `nextInt` de `NumerosAleatorios` para generar un número aleatorio entre 1 y 6, simulando así el lanzamiento de un dado de seis caras.

## Paso 3

Paso 3: Desarrollar un stub de NumerosAleatorios para usar en pruebas unitarias, permitiendo
controlar los resultados de los lanzamientos.

```java
package dados;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
Paso 3: Desarrollar un stub de NumerosAleatorios para usar en pruebas unitarias, permitiendo
controlar los resultados de los lanzamientos.
* */

class LanzamientoDadosTest {

    @Test
    void lanzarDados() {
        // Arrange
        NumerosAleatoriosStub stub = new NumerosAleatoriosStub();
        stub.setNextIntResult(5);
        LanzamientoDados lanzamiento = new LanzamientoDados(stub);

        // Act
        int resultado = lanzamiento.lanzar();

        // Assert
        assertThat(resultado).isEqualTo(6);
    }

    private static class NumerosAleatoriosStub implements NumerosAleatorios {
        private int nextIntResult;

        public void setNextIntResult(int nextIntResult) {
            this.nextIntResult = nextIntResult;
        }

        @Override
        public int nextInt(int bound) {
            return nextIntResult;
        }
    }
}
```

Explicación:

Desarrollamos un stub de la interfaz `NumerosAleatorios` para usar en nuestras pruebas unitarias. Este stub nos permite controlar los resultados de los lanzamientos de los dados, lo que es esencial para verificar que nuestro código funcione correctamente. En el método `lanzarDados`, creamos una instancia de `NumerosAleatoriosStub`, establecemos el resultado de `nextInt` en 5, y verificamos que el resultado del lanzamiento de los dados sea 6.

## Paso 4

Escribir pruebas unitarias para LanzamientoDados utilizando el stub para asegurar que la
lógica del lanzamiento funciona como se espera bajo condiciones controladas.

```java
@ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void lanzarDadosConDiferentesResultados(int resultadoEsperado) {
        // Arrange
        NumerosAleatoriosStub stub = new NumerosAleatoriosStub();
        stub.setNextIntResult(resultadoEsperado);
        LanzamientoDados lanzamiento = new LanzamientoDados(stub);

        // Act
        int resultado = lanzamiento.lanzar();

        // Assert
        assertThat(resultado).isEqualTo(resultadoEsperado + 1);
    }

```

Explicación:

Para escribir pruebas unitarias para `LanzamientoDados`, utilizamos el stub que desarrollamos en el paso anterior. Utilizamos una prueba parametrizada para probar diferentes resultados posibles de los lanzamientos de dados. En cada prueba, establecemos el resultado de `nextInt` en el valor del resultado esperado, lanzamos los dados, y verificamos que el resultado sea el resultado esperado más uno. 

También podemos utilizar Mock para crear el stub:

```java
package dados;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*
Paso 3: Desarrollar un stub de NumerosAleatorios para usar en pruebas unitarias, permitiendo
controlar los resultados de los lanzamientos.
* */

@ExtendWith(MockitoExtension.class)
class LanzamientoDadosTest {

    @Mock
    private NumerosAleatoriosStub stub;

    @InjectMocks
    private LanzamientoDados lanzamiento;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void lanzarDadosConDiferentesResultados(int resultadoEsperado) {

        when(stub.nextInt(6)).thenReturn(resultadoEsperado);

        //Act
        int resultado = lanzamiento.lanzar();

        //Assert
        assertThat(resultado).isEqualTo(resultadoEsperado + 1);
    }
}
```

Explicación:

En este caso estamos creando un Mock de NumerosAleatoriosStub y lo inyectamos en LanzamientoDados, luego creamos un stub con when para crear el stub y esperar el resultado esperado.

Salida:

![Untitled](Actividad%20Mocks%20y%20Stubs%2019a935f9578f47d6b3b4748ef6c7ba09/Untitled.png)

## Paso 5

Implementar una clase NumerosGeneradosAleatoriamente que utilice un generador de
números aleatorios real y que cumpla con la interfaz NumerosAleatorios.

```java
package dados;

import java.util.Random;

public class NumerosGeneradosAleatoriamente implements NumerosAleatorios {
    private final Random rnd = new Random();

    @Override
    public int nextInt(int upperBoundExclusive) {
        return rnd.nextInt(upperBoundExclusive);
    }
}

```

Explicación:

Implementamos una clase `NumerosGeneradosAleatoriamente` que utiliza un generador de números aleatorios real y cumple con la interfaz `NumerosAleatorios`. Esta clase será útil cuando queramos lanzar los dados en un entorno de producción real.

## Paso 6

Integrar y probar la clase LanzamientoDados en una aplicación de producción, inyectando la implementación real de NumerosAleatorios.

```java
package dados;

/*
 Paso 6: Integrar y probar la clase LanzamientoDados en una aplicación de producción,
inyectando la implementación real de NumerosAleatorios.
* */

public class Main {
    public static void main(String[] args) {
        NumerosAleatorios numerosAleatorios = new NumerosGeneradosAleatoriamente();
        LanzamientoDados lanzamientoDados = new LanzamientoDados(numerosAleatorios);
        System.out.println("Primer Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
        System.out.println("Segundo Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
        System.out.println("Tercer Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
        System.out.println("Cuarto Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
    }
}
```

Salida:

![Untitled](Actividad%20Mocks%20y%20Stubs%2019a935f9578f47d6b3b4748ef6c7ba09/Untitled%201.png)

# Sistema de notificaciones por email

Desarrollar una aplicación Java para gestionar el envío de notificaciones por email a usuarios, asegurando que los emails se envíen correctamente sin interacción con un servidor real durante las pruebas.

Requisitos funcionales:

1. Envío de Correo Electrónico: La aplicación debe permitir enviar un correo electrónico con un
destinatario, tema y texto especificados.
2. Verificación de Interacciones: Debe ser posible verificar que se ha llamado al método de envío
de correo electrónico con los argumentos correctos.
3. Facilidad de Pruebas: La arquitectura debe permitir la inyección de dependencias para facilitar
las pruebas, utilizando mocks para simular la interacción con el sistema de correo electrónico.

Requisitos no funcionales:

1. Extensibilidad: El sistema debe ser extensible para soportar diferentes tipos de notificaciones en
el futuro.
2. Mantenibilidad: El código debe ser fácil de mantener y comprender, aplicando principios SOLID
y patrones de diseño adecuados.

## Paso 1

Definir una interfaz MailServer que abstraiga el envío de correos electrónicos

```java
package email;

/*
Paso 1
Definir una interfaz MailServer que abstraiga el envío de correos electrónicos
* */

public interface MailServer {
    void sendEmail(String recipient, String subject, String message);
}
```

Explicación:

Definimos una interfaz `MailServer` que abstrae el envío de correos electrónicos. El método `sendEmail` acepta un destinatario, un asunto y un mensaje como parámetros. Esta abstracción nos permite cambiar la implementación de cómo se envían los correos electrónicos sin afectar el resto del código.

## Paso 2

Crear una clase UserNotifications que dependa de la interfaz MailServer para enviar correos.

```java
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
```

Explicación:

Creamos una clase `UserNotifications` que depende de la interfaz `MailServer` para enviar correos electrónicos. Inyectamos la dependencia de `MailServer` a través del constructor de `UserNotifications`, lo que permite una mayor flexibilidad y facilita las pruebas. El método `notifyUser` utiliza el método `sendEmail` de `MailServer` para enviar un correo electrónico al destinatario especificado, en caso de que algún campo se encuentre vacío lanza un excepción.

## Paso 3

Implementar un mock de MailServer que registre las llamadas a su método sendEmail y
capture los valores de los parámetros enviados.

```java
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
```

Explicación:

El mock de `MailServer` que desarrollamos registra las llamadas a su método `sendEmail` y captura los valores de los parámetros enviados. Esto nos permite verificar que `sendEmail` se llama con los argumentos correctos. En nuestro método de prueba, creamos una instancia de `UserNotifications`, llamamos al método `notifyUser`, y luego verificamos que `sendEmail` se llamó con el destinatario, asunto y mensaje esperados.

Salida al ejecutar el test:

## Paso 4

Escribir pruebas unitarias para UserNotifications utilizando el mock para verificar que los
correos se envíen correctamente.

![Untitled](Actividad%20Mocks%20y%20Stubs%2019a935f9578f47d6b3b4748ef6c7ba09/Untitled%202.png)

Podemos hacer algo similar usando mockito:

```java
package email;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserNotificationsTest {

    @Mock
    private MailServer mailServer;

    @InjectMocks
    private UserNotifications userNotifications;

    @Test
    void notifyUserSendsEmail(){
        String recipient = "luis.azana.v@uni.pe";
        String subject = "Test";
        String message = "This is a test";

        // Act
        userNotifications.notifyUser(recipient, subject, message);

        // Assert
        Mockito.verify(mailServer).sendEmail(recipient, subject, message);
    }
}
```

Explicación:

Utilizamos la anotación `@Mock` para crear un mock de `MailServer` y la anotación `@InjectMocks` para inyectar el mock en `UserNotifications`. En el método `notifyUserSendsEmail`, llamamos al método `notifyUser` y luego verificamos que `sendEmail` se llamó con los argumentos esperados utilizando el método `verify` de Mockito.

![Untitled](Actividad%20Mocks%20y%20Stubs%2019a935f9578f47d6b3b4748ef6c7ba09/Untitled%203.png)

## Parte 5

Implementar una clase RealMailServer que use SMTP para enviar correos en un entorno
de producción.

Implementamos la siguiente dependencia:

```java
// https://mvnrepository.com/artifact/com.sun.mail/jakarta.mail
implementation group: 'com.sun.mail', name: 'jakarta.mail', version: '2.0.1'
```

Luego creamos la clase RealMailServer:

```java
package email;

/*
Paso 5:
Implementar una clase RealMailServer que use SMTP para enviar correos en un entorno
de producción.
*/

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

import java.util.Properties;

public class RealMailServer implements MailServer{
    private final String smtpHost;
    private final int port;
    private final String username;
    private final String password;

        public RealMailServer(String smtpHost, int port, String username, String password) {
        this.smtpHost = smtpHost;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public void sendEmail(String recipient, String subject, String message) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", smtpHost);
        properties.setProperty("mail.smtp.port", String.valueOf(port));
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });

        try{
            Message msg = new jakarta.mail.internet.MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject(subject);
            msg.setText(message);

            Transport.send(msg);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Luego creamos una clase Main:

```java
package email;

public class Main {
    public static void main(String[] args) {
        //String smtpHost, int port, String username, String password
        String smtpHost = "smtp-mail.outlook.com";
        int port = 587;
        String username = "t*******@hotmail.com";
        String password = "********";
        MailServer mailServer = new RealMailServer(smtpHost, port, username, password);
        UserNotifications userNotifications = new UserNotifications(mailServer);
        userNotifications.notifyUser("luis.azana.v@uni.pe",
                "Mail de Prueba",
                "Este es un mensaje de prueba");
    }
}
```

Explicación:

Podemos observar que se ha recibido el correo de prueba correctamente, lo que indica que nuestra implementación de `RealMailServer` está funcionando como se esperaba en un entorno de producción. Este resultado valida nuestro diseño y la efectividad de nuestras pruebas unitarias.

Salida:

![Untitled](Actividad%20Mocks%20y%20Stubs%2019a935f9578f47d6b3b4748ef6c7ba09/Untitled%204.png)

En el mail del receptor:

![Untitled](Actividad%20Mocks%20y%20Stubs%2019a935f9578f47d6b3b4748ef6c7ba09/6fb33de8-67bf-4e62-9b90-524301ade2f0.png)