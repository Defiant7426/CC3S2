# Actividad Metricas de Calidad

¿Que es el acoplamiento eferente?

El acoplamiento eferente, también conocido como CE (Coupling Efferent), es una métrica en la ingeniería de software que se refiere a la cantidad de clases que una clase específica depende. En otras palabras, cuántas clases diferentes necesita una clase para realizar sus funciones.

¿Que es el acoplamiento aferente?

El acoplamiento aferente, también conocido como CA (Coupling Afferent), es otra métrica en la ingeniería de software que indica cuántas clases dependen de una clase específica. Es decir, cuántas clases usan las funciones de una clase en particular.

¿Que es el factor de acoplamiento?

El factor de acoplamiento es una métrica que combina el acoplamiento eferente y aferente para proporcionar una vista general de cómo una clase interactúa con otras. Este factor puede ayudar a los ingenieros de software a entender y minimizar las dependencias innecesarias en el código.

Se cacula como: CF = e / a * (a-1); donde e es el numero total de conexiones y a es el numero total de componentes

Ejemplo:

```java
// Módulo de Usuarios
class UsuarioModulo {
	private List<**Usuario**> usuarios = new ArrayList<>();
		public void agregarUsuario(Usuario usuario) {
			usuarios.add(usuario);
			GrupoContactoModulo.**agregarUsuarioAGrupo**(usuario, "General");
		}
		public void eliminarUsuario(String nombre) {
			usuarios.removeIf(u -> u.getNombre().equals(nombre));
	}
}
```

```java
// Módulo de Grupos de Contactos
class GrupoContactoModulo {
	static Map<String, List<**Usuario**>> grupos = new HashMap<>();
	static {
		grupos.put("General", new ArrayList<>());
	}
	public static void agregarUsuarioAGrupo(Usuario usuario, String grupoNombre) {
		grupos.get(grupoNombre).add(usuario);
	}
	public static void crearGrupo(String nombre) {
		if (!grupos.containsKey(nombre)) {
			grupos.put(nombre, new ArrayList<>());
		}
	}
}
```

```java
// Módulo de Operaciones de Contacto
class ContactoOperacionesModulo {
	public void enviarMensaje(String mensaje, Usuario usuario) {
		System.out.println("Enviando mensaje a " + usuario.getNombre() + ": " + mensaje);
	}
}
```

```java
// Clase Usuario
class Usuario {
	private String nombre;
	public Usuario(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
}
// Clase Principal para ejecutar el sistema
public class SistemaContactos {
 public static void main(String[] args) {
	 UsuarioModulo usuarioModulo = new UsuarioModulo();
	 Usuario nuevoUsuario = new Usuario("Juan");
	 usuarioModulo.agregarUsuario(nuevoUsuario);
	 ContactoOperacionesModulo operacionesModulo = new ContactoOperacionesModulo();
	 operacionesModulo.enviarMensaje("¡Hola!", nuevoUsuario);
	 GrupoContactoModulo.crearGrupo("Amigos");
	 GrupoContactoModulo.agregarUsuarioAGrupo(nuevoUsuario, "Amigos");
 }
}
```

Bien calculemos el factor de acoplamiento de estas clases:

Para la clase UsuarioModulo esta utilizando la clase Usuario y GrupoContactoModulo

Para la clase GrupoContactoModulo esta utilizando **Usuario**

Para la clase ContactoOperacionesModulo esta utilizando Usuario

En total hay un total de 4 conexiones y en total hay 3 componentes. Por lo tanto CF seria igual a 4 / 3 * (3 - 1) = 2/3

¿Qué es la inestabilidad?

La inestabilidad es una métrica que indica el nivel de cambios que una clase o módulo puede experimentar debido a cambios en otros módulos de la aplicación. Se calcula como I = CE / (CA + CE), donde CE es el acoplamiento eferente y CA es el acoplamiento aferente. Un valor de inestabilidad cercano a 0 indica un módulo estable, mientras que un valor cercano a 1 indica un módulo inestable.

Interpretación:

1. Valores bajos de I (cercanos a 0): Indican estabilidad, sugiriendo que el módulo es menos
propenso a cambios debido a sus dependencias internas.
2. Valores altos de I (cercanos a 1): Indican inestabilidad, sugiriendo que el módulo es más
propenso a cambios debido a sus dependencias externas

¿Qué es el LCOM?

El LCOM (Lack of Cohesion in Methods) es una métrica que mide la cohesión de una clase. Es decir, qué tan estrechamente relacionados están los métodos y variables de una clase. Valores altos de LCOM indican una baja cohesión y pueden sugerir que una clase está haciendo demasiadas cosas, lo que puede dificultar su mantenimiento y comprensión.

Se calcula como: LCOM = P - Q donde p es el numero de pares de métodos en la clase que no comparten atributos de instancia y Q es el numero de pares de métodos en la clase que si comparten atributos de instancia

```java
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

Aquí el LCOM es 10 - 0, el 10 viene de la combinatoria de 10 en 2, mientras que 0 es de que no existen pares de métodos que comparten atributos de instancia.

```java
package com.example;

import java.util.*;
public class LCOMCalculator {
	private static class ClassInfo {
		List<String> methods = new ArrayList<>();
		Map<String, Set<String>> methodAttributes = new HashMap<>();
		Set<String> attributes = new HashSet<>();
		public void addMethod(String methodName, Set<String> attrs) {
		methods.add(methodName);
		methodAttributes.put(methodName, attrs);
		attributes.addAll(attrs);
	}
	}
	public static void main(String[] args) {
		ClassInfo classInfo = new ClassInfo();
		// Simulación de entrada de métodos y sus accesos a atributos
		classInfo.addMethod("method1", new HashSet<>(Arrays.asList("attr1", "attr2")));
		classInfo.addMethod("method2", new HashSet<>(Arrays.asList("attr2")));
		classInfo.addMethod("method3", new HashSet<>(Arrays.asList("attr3")));
		int p = 0, q = 0;
		List<String> methods = classInfo.methods;
		for (int i = 0; i < methods.size(); i++) {
            for (int j = i + 1; j < methods.size(); j++) {
                String method1 = methods.get(i);
                String method2 = methods.get(j);
                Set<String> attrs1 = classInfo.methodAttributes.get(method1);
                Set<String> attrs2 = classInfo.methodAttributes.get(method2);
                // Calculamos si comparten atributos
                Set<String> intersection = new HashSet<>(attrs1);
                intersection.retainAll(attrs2);
                if (intersection.isEmpty()) {
                    p++; // No comparten atributos
                    System.out.println("No comparten atributos: " + method1 + " y " + method2);
                } else {
                    q++; // Comparten al menos un atributo
                    System.out.println("Comparten atributos: " + method1 + " y " + method2);
                }
		    }
		}
		int lcom = p - q;
    System.out.println("p = " + p);
    System.out.println("q = " + q);
		System.out.println("LCOM = " + lcom);
    }
}
```

En este codigo tenemos la siguiente salida:

![Untitled](Actividad%20Metricas%20de%20Calidad%2027e324018b4c4b8e917cad6830403da9/Untitled.png)

La razón para la que el LCOM es igual a 1 es porque el método 1 y 2 comparten solo 1 atributo, mientras que los métodos 1 con 3 y 2 con 3 no comparten atributos entre si.

Ahora vamos a extender el LCOM para incluir mas métodos y atributos de la siguiente manera:

```java
classInfo.addMethod("method1", new HashSet<>(Arrays.asList("attr1", "attr2")));
classInfo.addMethod("method2", new HashSet<>(Arrays.asList("attr2")));
classInfo.addMethod("method3", new HashSet<>(Arrays.asList("attr3")));
classInfo.addMethod("method4", new HashSet<>(Arrays.asList("attr4","attr5","attr6")));
classInfo.addMethod("method5", new HashSet<>(Arrays.asList("attr5","attr1")));
classInfo.addMethod("method6", new HashSet<>(Arrays.asList("attr6","attr2")));
classInfo.addMethod("method7", new HashSet<>(Arrays.asList("attr7","attr3")));
classInfo.addMethod("method8", new HashSet<>(Arrays.asList("attr8","attr4")));
```

![Untitled](Actividad%20Metricas%20de%20Calidad%2027e324018b4c4b8e917cad6830403da9/Untitled%201.png)

![Untitled](Actividad%20Metricas%20de%20Calidad%2027e324018b4c4b8e917cad6830403da9/Untitled%202.png)

De esta manera tenemos que el LCOM es 12.

Ahora procedemos con la refactorización:

```java
package com.example;

import java.util.*;

public class LCOMCalculator {
    public static class ClassInfo {
        List<String> methods = new ArrayList<>();
        Map<String, Set<String>> methodAttributes = new HashMap<>();
        Set<String> attributes = new HashSet<>();

        public void addMethod(String methodName, Set<String> attrs) {
            if (methodName == null || methodName.isEmpty() || attrs == null) {
                throw new IllegalArgumentException("Invalid method name or attributes");
            }
            methods.add(methodName);
            methodAttributes.put(methodName, attrs);
            attributes.addAll(attrs);
        }
    }

    public static void main(String[] args) {
        ClassInfo classInfo = new ClassInfo();
        // Simulación de entrada de métodos y sus accesos a atributos
        classInfo.addMethod("method1", new HashSet<>(Arrays.asList("attr1", "attr2")));
        classInfo.addMethod("method2", new HashSet<>(Arrays.asList("attr2")));
        classInfo.addMethod("method3", new HashSet<>(Arrays.asList("attr3")));
        classInfo.addMethod("method4", new HashSet<>(Arrays.asList("attr4","attr5","attr6")));
        classInfo.addMethod("method5", new HashSet<>(Arrays.asList("attr5","attr1")));
        classInfo.addMethod("method6", new HashSet<>(Arrays.asList("attr6","attr2")));
        classInfo.addMethod("method7", new HashSet<>(Arrays.asList("attr7","attr3")));
        classInfo.addMethod("method8", new HashSet<>(Arrays.asList("attr8","attr4")));
        int lcom = calculateLCOM(classInfo);
        System.out.println("LCOM = " + lcom);
    }

    public static int calculateLCOM(ClassInfo classInfo) {
        int p = 0, q = 0;
        List<String> methods = classInfo.methods;
        for (int i = 0; i < methods.size(); i++) {
            for (int j = i + 1; j < methods.size(); j++) {
                String method1 = methods.get(i);
                String method2 = methods.get(j);
                Set<String> attrs1 = classInfo.methodAttributes.get(method1);
                Set<String> attrs2 = classInfo.methodAttributes.get(method2);
                // Calculamos si comparten atributos
                Set<String> intersection = new HashSet<>(attrs1);
                intersection.retainAll(attrs2);
                if (intersection.isEmpty()) {
                    p++; // No comparten atributos
                    System.out.println("No comparten atributos: " + method1 + " y " + method2);
                } else {
                    q++; // Comparten al menos un atributo
                    System.out.println("Comparten atributos: " + method1 + " y " + method2);
                }
            }
        }
        return Math.max(p - q, 0);
    }
}
```

Cambios realizados en el código:

1. Se creó un método separado llamado calculateLCOM que realiza los cálculos del LCOM, lo que permite una mayor reutilización del código y una mayor legibilidad.
2. Se añadió una comprobación para asegurar que el nombre del método y los atributos pasados al método addMethod no son nulos o vacíos, mejorando la robustez del código.
3. Se retorna entre el máximo de p-q y 0, ya que si el lcom es negativo retornamos 0

¿Qué es el LCOM4?

LCOM4 es una métrica de cohesión que cuenta los conjuntos de métodos en una clase que son mutuamente inaccesibles, es decir, mide los subgrupos de métodos sin conexión a través de campos compartidos. Cada conjunto sugiere un componente separable, indicando que la clase puede tener demasiadas funciones y debería dividirse. LCOM4 mide la cohesión en componentes conectados dentro de una clase. Fórmula: LCOM4= Número de componentes conectados.

Interpretación:

1. LCOM4 = 1: La clase es cohesiva y todos los métodos están conectados a través de campos
compartidos.
2. LCOM4 > 1: La clase tiene múltiples componentes conectados, indicando que la clase puede
estar realizando demasiadas funciones y podría beneficiarse de una refactorización para
aumentar la cohesión

Ejemplo:

```java
public class ExampleClass {
	private int a;
	private int b;
	public void method1() {
		a = 1;
	}
	public void method2() {
		b = 2;
	}
	public void method3() {
		a = 3;
		b = 3;
	}
	public void method4() {
		// No accede a ningún campo
	}
}
```

Bien, ahora vamos a escribir el código para calcular el LCOM4:

```java
package com.example;

import java.util.*;

public class LCOM4Calculator {
    private static class ClassInfo {
        List<String> methods = new ArrayList<>();
        Map<String, Set<String>> methodAttributes = new HashMap<>();
        Set<String> attributes = new HashSet<>();
        public void addMethod(String methodName, Set<String> attrs) {
            methods.add(methodName);
            methodAttributes.put(methodName, attrs);
            attributes.addAll(attrs);
        }
        public int calculateLCOM4() {
            Map<String, Set<String>> graph = new HashMap<>();
            for (String method : methods) {
                graph.put(method, new HashSet<>());
            }
            for (int i = 0; i < methods.size(); i++) {
                for (int j = i + 1; j < methods.size(); j++) {
                    String method1 = methods.get(i);
                    String method2 = methods.get(j);
                    Set<String> attrs1 = methodAttributes.get(method1);
                    Set<String> attrs2 = methodAttributes.get(method2);
                    // Crear un arco si comparten atributos
                    if (!Collections.disjoint(attrs1, attrs2)) {
                        graph.get(method1).add(method2);
                        graph.get(method2).add(method1);
                    }
                }
            }
            return countComponents(graph);
        }
        private int countComponents(Map<String, Set<String>> graph) {
        Set<String> visited = new HashSet<>();
        int components = 0;
        for (String method : methods) {
            if (!visited.contains(method)) {
                dfs(graph, method, visited);
                components++;
            }
        }
        return components;
        }
        private void dfs(Map<String, Set<String>> graph, String method, Set<String> visited) {
            Stack<String> stack = new Stack<>();
            stack.push(method);
            while (!stack.isEmpty()) {
                String current = stack.pop();
                if (!visited.contains(current)) {
                    visited.add(current);
                    for (String neighbor : graph.get(current)) {
                        if (!visited.contains(neighbor)) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        ClassInfo classInfo = new ClassInfo();
        // Ejemplo de métodos y atributos
        classInfo.addMethod("method1", new HashSet<>(Arrays.asList("attr1", "attr2")));
        classInfo.addMethod("method2", new HashSet<>(Arrays.asList("attr2")));
        classInfo.addMethod("method3", new HashSet<>(Arrays.asList("attr3")));
        int lcom4 = classInfo.calculateLCOM4();
        System.out.println("LCOM4 = " + lcom4);
    }
}
```

Explicación del código:

El código anterior calcula LCOM4 mediante la construcción de un gráfico en el que cada método es un nodo y cada par de métodos que comparten al menos un atributo están conectados por un arco. Luego, realiza una búsqueda en profundidad (DFS) en este gráfico para contar el número de componentes conectados, lo que da como resultado el valor LCOM4. Un componente conectado es un subconjunto de métodos que están todos interconectados a través de atributos compartidos.

La salida es la siguiente:

![Untitled](Actividad%20Metricas%20de%20Calidad%2027e324018b4c4b8e917cad6830403da9/Untitled%203.png)

En esta ocacion el LCOM4 es igual a 2 ya que existen dos componentes conectados en la clase de ejemplo. El primer componente es formado por los métodos "method1" y "method2", que comparten el atributo "attr2". El segundo componente es el método "method3", que no comparte ningún atributo con los otros métodos.

Ahora tenemos el archivo [CineManager.java](http://CineManager.java) dada por la actividad de la siguiente manera:

```java
package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class CineManager {
    private List<Pelicula> peliculas;
    private Map<Integer, Sala> salas;

    public CineManager() {
        peliculas = new ArrayList<>();
        salas = new HashMap<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    public boolean eliminarPelicula(String titulo) {
        return peliculas.removeIf(p -> p.getTitulo().equals(titulo));
    }

    public void agregarSala(Sala sala) {
        salas.put(sala.getId(), sala);
    }

    public boolean eliminarSala(int id) {
        return salas.remove(id) != null;
    }

    public List<Sesion> sesionesPorPelicula(String titulo) {
        return peliculas.stream()
                .filter(p -> p.getTitulo().equals(titulo))
                .flatMap(p -> p.getSesiones().stream())
                .collect(Collectors.toList());
    }
    public void programarSesion(String titulo, Sesion sesion) {
        peliculas.stream()
                .filter(p -> p.getTitulo().equals(titulo))
                .findFirst()
                .ifPresent(p -> p.agregarSesion(sesion));
    }

    public int contarPeliculas() {
        return peliculas.size();
    }

    public int contarSalas() {
        return salas.size();
    }
}

class Pelicula {
    private String titulo;
    private List<Sesion> sesiones;

    public Pelicula(String titulo) {
        this.titulo = titulo;
        sesiones = new ArrayList<>();
    }

    public void agregarSesion(Sesion sesion) {
        sesiones.add(sesion);
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Sesion> getSesiones() {
        return new ArrayList<>(sesiones);
    }
}

class Sala {
    private int id;
    private int capacidad;

    public Sala(int id, int capacidad) {
        this.id = id;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }
}

class Sesion {
    private String horaInicio;
    private int duracion; // Duración en minutos
    public Sesion(String horaInicio, int duracion) {
        this.horaInicio = horaInicio;
        this.duracion = duracion;
    }
}
```

Vamos a probar este archivo lo vamos a probar en nuestra clase LCOM4Calculator, para ello cambiamos el metodo main como sigue:

```java
public static void main(String[] args) {
	ClassInfo cineManagerInfo = new ClassInfo();
	// Agregar los métodos y atributos de la clase CineManager
	cineManagerInfo.addMethod("agregarPelicula", new HashSet<>(Arrays.asList("peliculas")));
	cineManagerInfo.addMethod("eliminarPelicula", new HashSet<>(Arrays.asList("peliculas")));
	cineManagerInfo.addMethod("agregarSala", new HashSet<>(Arrays.asList("salas")));
	cineManagerInfo.addMethod("eliminarSala", new HashSet<>(Arrays.asList("salas")));
	cineManagerInfo.addMethod("sesionesPorPelicula", new HashSet<>(Arrays.asList("peliculas")));
	cineManagerInfo.addMethod("programarSesion", new HashSet<>(Arrays.asList("peliculas")));
	cineManagerInfo.addMethod("contarPeliculas", new HashSet<>(Arrays.asList("peliculas")));
	cineManagerInfo.addMethod("contarSalas", new HashSet<>(Arrays.asList("salas")));
	int lcom4 = cineManagerInfo.calculateLCOM4();
	System.out.println("LCOM4 = " + lcom4);
}
```

La salida es la siguiente:

![Untitled](Actividad%20Metricas%20de%20Calidad%2027e324018b4c4b8e917cad6830403da9/Untitled%204.png)

En este caso el LCOM4 es igual a 2, ya que tenemos dos componentes conectados que son los métodos que utilizan el atributo "peliculas" y los métodos que utilizan el atributo "salas". Esto indica que la clase CineManager tiene dos responsabilidades principales: gestionar películas y gestionar salas. Lo ideal es de que LCOM sea 1 entonces realizaremos el siguiente cambio:

Podemos dividir la clase CineManager en dos clases separadas: PeliculaManager y SalaManager, cada una encargada de gestionar películas y salas, respectivamente. De esta manera, cada clase tendrá una única responsabilidad, mejorando la cohesión y haciendo que el código sea más fácil de mantener y entender.

```java
package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class PeliculaManager {
    private List<Pelicula> peliculas;

    public PeliculaManager() {
        peliculas = new ArrayList<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    public boolean eliminarPelicula(String titulo) {
        return peliculas.removeIf(p -> p.getTitulo().equals(titulo));
    }

    public List<Sesion> sesionesPorPelicula(String titulo) {
        return peliculas.stream()
                .filter(p -> p.getTitulo().equals(titulo))
                .flatMap(p -> p.getSesiones().stream())
                .collect(Collectors.toList());
    }

    public void programarSesion(String titulo, Sesion sesion) {
        peliculas.stream()
                .filter(p -> p.getTitulo().equals(titulo))
                .findFirst()
                .ifPresent(p -> p.agregarSesion(sesion));
    }

    public int contarPeliculas() {
        return peliculas.size();
    }
}
```

```java
package com.example;

import java.util.*;

public class SalaManager {
    private Map<Integer, Sala> salas;

    public SalaManager() {
        salas = new HashMap<>();
    }

    public void agregarSala(Sala sala) {
        salas.put(sala.getId(), sala);
    }

    public boolean eliminarSala(int id) {
        return salas.remove(id) != null;
    }

    public int contarSalas() {
        return salas.size();
    }
}
```

En este caso ahora que son clases separadas al evaluar LCOM4 con el siguiente codigo:

```java
public static void main(String[] args) {
	// Para SalaManager
	ClassInfo salaManagerInfo = new ClassInfo();
	salaManagerInfo.addMethod("agregarSala", new HashSet<>(Arrays.asList("salas")));
	salaManagerInfo.addMethod("eliminarSala", new HashSet<>(Arrays.asList("salas")));
	salaManagerInfo.addMethod("contarSalas", new HashSet<>(Arrays.asList("salas")));
	int lcom4SalaManager = salaManagerInfo.calculateLCOM4();
	System.out.println("LCOM4 de SalaManager = " + lcom4SalaManager);
	
	// Para PeliculaManager
	ClassInfo peliculaManagerInfo = new ClassInfo();
	peliculaManagerInfo.addMethod("agregarPelicula", new HashSet<>(Arrays.asList("peliculas")));
	peliculaManagerInfo.addMethod("eliminarPelicula", new HashSet<>(Arrays.asList("peliculas")));
	peliculaManagerInfo.addMethod("sesionesPorPelicula", new HashSet<>(Arrays.asList("peliculas")));
	peliculaManagerInfo.addMethod("programarSesion", new HashSet<>(Arrays.asList("peliculas")));
	peliculaManagerInfo.addMethod("contarPeliculas", new HashSet<>(Arrays.asList("peliculas")));
	int lcom4PeliculaManager = peliculaManagerInfo.calculateLCOM4();
	System.out.println("LCOM4 de PeliculaManager = " + lcom4PeliculaManager);
 }
```

La salida es la siguiente:

![Untitled](Actividad%20Metricas%20de%20Calidad%2027e324018b4c4b8e917cad6830403da9/Untitled%205.png)

¿Que es CAMC?

CAMC, o Cohesion Among Methods of Class, es una métrica de cohesión que evalúa la similitud de los tipos de parámetros de los métodos de una clase. CAMC mide la cohesión en términos de la uniformidad de los tipos de parámetros de los métodos en una clase. Una alta cohesión (cercana a 1) indica que los métodos en la clase son altamente cohesivos y viceversa.

CAMC = [sumatorio de tipos de parámetros únicos utilizados por todos los métodos] /[número total de métodos× número máximo de parámetros por método.]

Ejemplo:

```java
public class ExampleClass {
 public void method1(int a, String b) { }
 public void method2(int a, double c) { }
 public void method3(String b, double c) { }
}
```

1. Tipos de parámetros únicos por método:
    1. method1 utiliza int y String (2 tipos únicos)
    2. method2 utiliza int y double (2 tipos únicos)
    3. method3 utiliza String y double (2 tipos únicos)
2. Suma de tipos de parámetros únicos:
    1. 2(method1)+2(method2)+2(method3)=6
3. Número total de métodos: 3
4.  Número máximo de parámetros por método: 2

CAMC=6/3 *2 =6/6 =1

Un CAMC de 1 indica que la clase tiene alta cohesión en términos de los tipos de parámetros que utilizan  sus métodos.

Interpretación

1. CAMC cercano a 1: Indica alta cohesión, sugiriendo que los métodos de la clase están
estrechamente relacionados en términos de los tipos de datos que manejan.
2. CAMC cercano a 0: Indica baja cohesión, sugiriendo que los métodos de la clase manejan tipos
de datos muy diferentes y podrían estar realizando funciones no relacionadas.

¿Que es la Complejidad ciclomática?

La Complejidad Ciclomática es una métrica de software que cuantifica la complejidad de un programa. Fue desarrollada por Thomas J. McCabe y se utiliza para indicar la complejidad de un programa mediante el conteo de los caminos independientes a través del código fuente. Un alto valor de complejidad ciclomática indica un código potencialmente más difícil de entender, probar y mantener.

1. Nodos: Representan las partes del código donde se ejecuta la acción (por ejemplo, ejecución de
una instrucción).
2. Aristas: Representan el flujo de control de una parte del código a otra (por ejemplo, una
declaración de salto como un if o un loop).

Valores típicos:

1. 0-10: Generalmente se considera un buen rango de complejidad, indicando que el código es
probablemente fácil de entender y mantener.
2. 11-20: Indica una complejidad moderada; aunque aún manejable, el código en este rango podría beneficiarse de cierta refactorización.
3. 21+: Representa una alta complejidad y es una señal de alerta para revisar el diseño del código.
A menudo, el código en este rango se beneficia significativamente de ser dividido en
componentes más pequeños.

La complejidad ciclomática se calcula mediante la fórmula:
M=E−N+2P
donde:
E = el número de aristas del grafo.
N = el número de nodos del grafo.
P = el número de componentes conectados.