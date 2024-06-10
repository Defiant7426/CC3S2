# Actividad-Refactorización

Alumno: De la Cruz Valdiviezo, Pedro Luis David

# Ejercicio 1

La actividad nos proporciona un código inicial:

```java
package ejercicio2;

public class EmployeeManager {

    public void addEmployee(String name, String department) {
        // Añade un empleado al departamento
        System.out.println("Empleado añadido");
    }

    public void removeEmployee(String name) {
        // Elimina un empleado
        System.out.println("Empleado eliminado");
    }

    public void changeDepartment(String employeeName, String newDepartment) {
        // Cambia un empleado de departamento
        System.out.println("Departamento cambiado");
    }

    public void printDepartmentReport(String department) {
        // Imprime un informe del departamento
        System.out.println("Informe del departamento " + department);
    }

    public void printAllDepartments() {
        // Imprime todos los departamentos
        System.out.println("Lista de todos los departamentos");
    }
}
```

Notamos que esta clase tiene posee una baja cohesión ya que contiene varias responsabilidades como la gestión de empleados y departamentos. Refactorizando el código, podríamos mejorar la cohesión dividiendo la clase en dos: una clase EmployeeManager y otra clase DepartmentManager, cada una con sus responsabilidades específicas.

Empecemos primero creando una clase llamada Employee:

```java
package ejercicio2;

public class Employee {
    private String name;
    private String department;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
```

Este código crea una clase Employee con dos atributos: nombre y departamento. También proporciona métodos getter y setter para estos atributos, permitiendo su acceso y modificación.

Pero tambien podemos hacer una clase departamento lo cual se hace mas legible:

```java
package ejercicio2;

public class Department {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }   
}

```

Entonces la clase Employee que escribimos anteriormente quedaria de la siguiente forma:

```java
package ejercicio2;

public class Employee {
    private String name;
    private Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

```

En este código, hemos cambiado el atributo "department" de tipo String a tipo Department, permitiendo una mejor asociación entre los empleados y sus respectivos departamentos.

Ahora crearemos la clase `DepartmentManager` donde será la clase responsable de administrar los departamentos, agregando o imprimiendo los departamentos existentes

```java
package ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class DepartmentManager {
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(String department) {
        departments.add(new Department(department));
    }

    public Department getDepartment(String department) {
        return departments.stream()
                .filter(depart -> depart.getName().equals(department))
                .findFirst()
                .orElse(null);
    }

    public void printAllDepartments() {
        for (Department department : departments) {
            System.out.println(department.getName());
        }
    }
}
```

Ahora hacemos la clase `EmployeeManager` con un constructor que recibe un `DepartmentManager` donde se encuentran todos los departamentos, luego el método `addEmployee` agrega a un empleado siempre y cuando el departamento exista, si no es así el método lanza una excepción. El método `removeEmployee` elimina un empleado. `changeDepartment` cambia el departamento de un empleado y por ultimo el método `printAllEmployees` nos muestra todos los empleados.

```java
package ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private List<Employee> employees = new ArrayList<>();
    private DepartmentManager departmentManager;

    public EmployeeManager(DepartmentManager departmentManager) {

        this.departmentManager = departmentManager;
    }

    public void addEmployee(String name, String department) {
        if(departmentManager.getDepartment(department) == null) {
            throw new IllegalArgumentException("Department does not exist.");
        }
        employees.add(new Employee(name, departmentManager.getDepartment(department)));
    }

    public void removeEmployee(String name) {

        employees.removeIf(employee -> employee.getName().equals(name));
    }

    public void changeDepartment(String employeeName, String newDepartment) {
        employees.stream()
                .filter(employee -> employee.getName().equals(employeeName))
                .forEach(employee -> employee.setDepartment(new Department(newDepartment)));
    }

    public void printAllEmployees() {
        System.out.println("\nAll Employees:");
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " - " + employee.getDepartment().getName());
        }
        System.out.println();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
```

Finalmente creamos la clase Report donde vamos a mostrar el resporte respecto a un departamento:

```java
package ejercicio2;

public class Report {

    private EmployeeManager employeeManager;

    public Report(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public void printDepartmentReport(String departmentName) {
        System.out.println("Employees in department " + departmentName);
        for (Employee employee : employeeManager.getEmployees()) {
            if (employee.getDepartment().getName().equals(departmentName)) {
                System.out.println(employee.getName());
            }
        }
    }
}
```

Ahora la clase Main será la siguiente:

```java
package ejercicio2;

public class Main {
    public static void main(String[] args) {
        DepartmentManager departmentManager = new DepartmentManager();
        departmentManager.addDepartment("IT");
        departmentManager.addDepartment("HR");
        departmentManager.addDepartment("Finance");
        departmentManager.addDepartment("Marketing");
        departmentManager.printAllDepartments();

        EmployeeManager employeeManager = new EmployeeManager(departmentManager);
        employeeManager.addEmployee("John", "IT");
        employeeManager.addEmployee("Jane", "HR");
        employeeManager.addEmployee("Doe", "Finance");
        employeeManager.addEmployee("Smith", "Marketing");
        employeeManager.addEmployee("Jannice", "IT");
        employeeManager.addEmployee("Fernanda", "HR");
        employeeManager.addEmployee("David", "Finance");
        employeeManager.addEmployee("Luis", "Marketing");

        employeeManager.printAllEmployees();

        employeeManager.removeEmployee("Jannice");
        employeeManager.changeDepartment("David", "IT");

        employeeManager.printAllEmployees();

        Report report = new Report(employeeManager);
        report.printDepartmentReport("IT");
    }
}
```

Primero creamos el objeto departmentManager y agregamos nuevos departamentos con el método addDepartment y luego mostramos todos los departamentos. Luego usamos employeeManager para agregar los empleados con sus respectivos departamentos previamente, luego eliminamos el empleado con nombre Jannice, y cambiamos el departamento de David a IT para finalmente mostrar el reporte final por departamento

Salida:

![Untitled](Actividad-Refactorizacio%CC%81n%2096b2f2dd78e14625aad4f9ae0ffad08b/Untitled.png)

![Untitled](Actividad-Refactorizacio%CC%81n%2096b2f2dd78e14625aad4f9ae0ffad08b/Untitled%201.png)

# Ejercicio 2

En este ejercicio, contamos con una clase denominada NotificationManager. Esta clase es responsable de gestionar las notificaciones. Posee un constructor que recibe dos atributos: el usuario y el mensaje que deseamos enviar.

Posteriormente, tenemos un método llamado sendNotification. Este se encarga de enviar la notificación, pero para ello necesita especificar el tipo de envío. Existen tres tipos: "email", "sms" y "app".

```java
public class NotificationManager {
	private String user;
	private String message;
	public NotificationManager(String user, String message) {
		this.user = user;
		this.message = message;
	}
	public void sendNotification(String type) {
		if ("email".equals(type)) {
			System.out.println("Enviando email a " + user + " con mensaje: " + message);
		} else if ("sms".equals(type)) {
			System.out.println("Enviando sms a " + user + " con mensaje: " + message);
		} else if ("app".equals(type)) {
			System.out.println("Enviando notificación de app a " + user + " con mensaje: " + message);
		}
	}
}
```

Para mejorar la legibilidad y mantenibilidad del código, aplicaremos el patrón de diseño de method factory.

Primero empezamos creando la clase abstracta Notification

```java
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
```

Explicación:

Hemos definido una clase abstracta Notification que acepta el usuario y el mensaje como parámetros. Además, incluye un método abstracto sendNotification(), que debe ser implementado por las clases hijas. También hemos agregado métodos getter para el usuario y el mensaje, permitiendo su acceso desde las clases derivadas.

Ahora implementamos tres clases hijas AppNotification:

```java
public class AppNotification extends Notification{

    public AppNotification(String user, String message) {
        super(user, message);
    }

    @Override
    public void sendNotification() {
        System.out.println("Enviando notificación de app a " + getUser() + " con mensaje: " + getMessage());
    }
}
```

MailNotification:

```java
public class MailNotification extends Notification {

    public MailNotification(String user, String message) {
        super(user, message);
    }

    @Override
    public void sendNotification() {
        System.out.println("Enviando email a " + getUser() + " con mensaje: " + getMessage());
    }
}
```

SmsNotification:

```java
public class SmsNotification extends Notification {

    public SmsNotification(String user, String message) {
        super(user, message);
    }

    @Override
    public void sendNotification() {
        System.out.println("Enviando sms a " + getUser() + " con mensaje: " + getMessage());
    }

}
```

Explicacion:

Cada una de estas clases hereda de Notification y sobrescribe el método sendNotification(). Este método imprime un mensaje específico según el tipo de notificación. Ahora, en lugar de chequear el tipo de notificación en un único método, tenemos una clase específica para manejar cada tipo, lo cual incrementa la modularidad del código.

Ahora vamos a cambiar nuestra clase principal NotificationManager:

```java
public class NotificationManager {
    private String user;
    private String message;
    private Notification notification;
    public NotificationManager(String user, String message) {
        this.user = user;
        this.message = message;
    }
    public void sendNotification(String type) {
        if (type.equals("email")) {
            notification = new MailNotification(user, message);
        } else if (type.equals("sms")) {
            notification = new SmsNotification(user, message);
        } else {
            notification = new AppNotification(user, message);
        }
        notification.sendNotification();
    }
}
```

Explicación:

En este nuevo diseño, la clase NotificationManager utiliza composición en lugar de herencia. En lugar de ser responsable de enviar la notificación, delega esa responsabilidad a la clase Notification, que se selecciona en función del tipo de notificación requerida. Esta refactorización mejora la legibilidad, modularidad y mantenibilidad del código.

Podemos reemplazar el uso de if y else usando una colección map, donde las llaves son String y los valores son de tipo notificación:

```java
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
```

El mapeo es creado en el constructor de notificationManager para 

Si es que el mapeo no encuentra el tipo especificado entonces lanzamos una excepción mostrando un mensaje informándonos que el tipo de notificación es invalida

Ahora vamos a probarlo en un método main de la siguiente manera:

```java
public class Main {
    public static void main(String[] args) {
        NotificationManager notificationManager = new NotificationManager("Pedro", "Su registro es exitoso");
        notificationManager.sendNotification("email");
        notificationManager.sendNotification("sms");
        notificationManager.sendNotification("app");
    }
}
```

La salida sera la siguiente:

![Untitled](Actividad-Refactorizacio%CC%81n%2096b2f2dd78e14625aad4f9ae0ffad08b/Untitled%202.png)

# Ejercicio 3

Para este ejercicio vamos a aplicar los principios SOLID, el código inicial para esta actividad es la siguiente:

el código inicial para la actividad es la siguiente:

```java
public class BlogManager {
		private List<String> articles = new ArrayList<>();
		public void addArticle(String article) {
				if (article != null && !article.isEmpty()) {
						articles.add(article);
						System.out.println("Artículo añadido: " + article);
						saveArticleToDatabase(article);
				}
		} 
		private void saveArticleToDatabase(String article) {
				// Simulación de guardar en la base de datos
				System.out.println("Guardando en la base de datos: " + article);
				}
				public void printAllArticles() {
						for (String article : articles) {
								System.out.println("Artículo: " + article);
						}
		}
}
```

Lo que haremos es crear una clase articulo:

```java
package ejercicio3;

public class Article {
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
```

Esta clase representa un articulo y se define dando su titulo y su contenido.

Ahora vamos a definir una interfaz `IDataBases` donde vamos a señalar las funcionalidades de la base de datos:

```java
package ejercicio3;

import java.util.List;

public interface IDataBases {
    void save(Article article);
    Article findById(int id);
    List<Article> findAll();
    long count();
    void delete(Article article);
}
```

Ahora definimos la implementacion de la siguiente manera:

```java
package ejercicio3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBases implements IDataBases{

    private Map<Integer, Article> articleMap = new HashMap<>();

    @Override
    public void save(Article article) {
        articleMap.put(articleMap.size(), article);
        System.out.println("Articulo guardado");
    }

    @Override
    public Article findById(int id) {
        return articleMap.get(id);
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articleMap.values());
    }

    @Override
    public long count() {
        return articleMap.size();
    }

    @Override
    public void delete(Article article) {
        articleMap.values().remove(article);
        System.out.println("Articulo eliminado");
    }
}
```

y por ultimo el BlogManager:

```java
package ejercicio3;

public class BlogManager {
    private IDataBases dataBases = new DataBases();

    public void saveArticle(String title, String content) {
        Article article = new Article(title, content);
        dataBases.save(article);
    }

    public void deleteArticle(Article article) {
        dataBases.delete(article);
    }

    public Article findArticle(int id) {
        return dataBases.findById(id);
    }

    public long countArticles() {
        return dataBases.count();
    }

    public void printAllArticles() {
        for (Article article : dataBases.findAll()) {
            System.out.println("Title: " + article.getTitle() + " Content: " + article.getContent());
        }
    }
}
```

Finalmente el Main:

```java
package ejercicio3;

public class Main {
    public static void main(String[] args) {
        BlogManager blogManager = new BlogManager();
        blogManager.saveArticle("Title 1", "Content 1");
        blogManager.saveArticle("Title 2", "Content 2");
        blogManager.saveArticle("Title 3", "Content 3");
        blogManager.printAllArticles();
        System.out.println("Count: " + blogManager.countArticles());
        Article article = blogManager.findArticle(2);
        System.out.println("Title: " + article.getTitle() + " Content: " + article.getContent());
        blogManager.deleteArticle(article);
        blogManager.printAllArticles();
        System.out.println("Count: " + blogManager.countArticles());
    }
}
```

Con estos cambios, estamos asegurando que cada clase se encargue de una única responsabilidad. Además, con la interfaz de la base de datos, estamos cumpliendo el segundo principio SOLID, que establece que una clase debe estar cerrada para la modificación pero abierta para la extensión.

Salida:

![Untitled](Actividad-Refactorizacio%CC%81n%2096b2f2dd78e14625aad4f9ae0ffad08b/Untitled%203.png)