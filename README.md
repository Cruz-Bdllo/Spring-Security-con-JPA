# Spring Security, Spring MVC y JPA
## ¿De que trata? ##
Esta pequeña práctica muestra como integrar una seguridad básica en nuestras aplicaciones web, utilizando el framework de **Spring Security**.
El cual permite establecer usuarios y roles para el acceso a ciertos recursos de nuestra aplicación (estos recursos pueden ser páginas o directorios).

**Cabe aclarar que este es solo un proyecto de práctica por lo que la creación y definiciones usadas en este documento no son las de una persona con gran experiencia, solo soy un principiante**
## ¿Como se desarrollo? ##
1.  Crear un proyecto nuevo de spring (se sugiere hacerlo desde la página de initializr: https://start.spring.io/) y seleccionar las siguientes dependencias:
	- Spring Boot DevTools.
    - Spring Web.
    - Thymeleaf.
    - Spring Data JPA.
    - MySQL Driver.
2. Se establecio el diagrama de Entidad Relación, que permitiera ver la relación entre los usuarios y a lo que tenian permitido acceder (se puede mejorar haciendo uso de reestricciones).
	- Se recomienda poblar de algunos datos a la tabla de authorities.
	- ![Diagrama][img-diagrama]
3. Generar la estructura de los paquetes del proyecto:
	- entities: Guardaremos todas las clases pojo.
    - repositories: Son interfaces propias que extienden de interfaces que proporciona Spring y que nos permiten hacer uso de operaciones CRUD.
    - services: Cuentan con una interfaz y una clase de servicio (@Service) por cada entidad.
    - controllers: Clases de tipo controller (@Controller) para permitirnos manejar las peticiones.
4. Dado el anterior diagrama podemos observar que se requieren 2 entidades, estas dos entidades seran las que estaran mapeadas directamente con la tabla que le corresponda en la BD **(no se incluye la tabla transitiva)**.
	- Para esta práctica solo se requiere la relación de users a authorities **(siendo que un usuario cuenta con diversos accesos - o almenos asi lo especifique)**
	- ![Diagrama][img-diagrama]
5. Se definio que cada entidad tuviera un repositorio y que ambos extendieran de **CrudRepository<T, ID>**
6. En el paquete de services se crearón una interfaz y una clase por cada interfaz en repositories.
	- La interfaz en service permite emvolver con un nombre más comodo a los métodos de **CrudRepository** sirviendo como intermediario para no usar directamente el repositorio.
    - La clase de servicio implementa los métodos de la interfaz en service.
7. Generar un controlador (en este caso cree uno para usuarios y otro para home) e indicar a Spring que se trata de un controlador mediante la anotación ** @Controller **
	- Definir una ruta hacia un formulario para crear un nuevo usuario, en este formulario mandaremos al modelo todos los roles que hay en la tabla authorities.
8. Generar en template una carpeta **includes** que dentro tenga una plantilla y esta contenga los componentes que mas se pudieran repetir en las vistas en este caso la plantilla contiene los componentes:
	- head: contiene toda la informacion entre las etiquetas `<head></head>` (titulo, archivos css, etc).
    - header: Contiene el menu de navegación entre las etiquetas `<header></header>`.
    - footer: Contiene solo el pie de página entre las etiquetas `<footer></footer>`.
    - scripts: En este caso como se hace uso de **bootstrap** se requieren los scripts para que funcione las animaciones entre las etiquetas `<script th:src="@{}"></script>`. 
    - Se requiere el uso de `<html lang="es" xmlns:th="https://www.thymeleaf.org">` para poder usar las directivas de **thymeleaf** (como th:text, th:fragment, th:replace, etc).
9. Crear una clase de configuración a nivel de la clase principal, en la clase de configuración crearemos un **@Bean** para poder encriptar contraseñas el bean retornara un **BCryptPasswordEncoder** 
	
    `protected BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }`
10. En el controlador inyectaremos el bean creado para encryptar las contraseñas cuando se cree un nuevo usuario.
11. Una vez guardado un usuario (con la contraseña encriptada) integramos **Spring Security** a nuestro proyecto atraves del archivo **pom.xml** como una nueva dependencia:
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
    
12. Crear una clase para configurar la seguridad de las rutas y el servicio proveedor esta clase debe de extender de **WebSecurityConfigurerAdapter** y sobre escribir los siguientes métodos:
    - `protected void configure(AuthenticationManagerBuilder auth) throws Exception {}`
    - `protected void configure(HttpSecurity http) throws Exception {}`
13. Para el primer método sobre escrito indicamos que se autentique por medio de una BD y que la contraseña que ingresemos al login la encripte con el bean antes creado, para ello usamos el método de auth siendo: `auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)` en el metodo `userDetailsService()` debemos inyectar un objeto que implemente `UserDetailsService` para ello: 
	- Creamos una clase de servicio llamada **MyUserDetailsService** y la denotamos con la anotación **@Service** en esta clase implementaremos el único método que tiene la interfaz **UserDetailsService** `public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{}` en este método buscaremos y regresaremos un registro que concuerde con el username (en este caso lo buscaremos de acuerdo al RFC).
    - Una vez que la consulta regrese al objeto User retornaremos un objeto que implemente **UserDetails** con un parametro en su constructor de tipo **User**, por lo que crearemos esta clase con el nombre de **MyUserDetails**.
    - En **MyUserDetails** implementaremos la interfaz `UserDetails` con los siguientes parámetros:
        1. email.
        2. password.
        3. activo.
        4. authorityList (contendra los roles que se le asignarón).
    - Definimos un método constructor que reciba un objeto de tipo **User**, para poblar los atributos de la clase **MyUserDetails** con la del usuario de la base de datos.
    - Implementamos los métodos de la interfaz `UserDetails` estos métodos son getters que corresponden a los atributos de nuestra clase.
    - De esta manera Spring Security comprueba las credenciales y los lugares en los que puede accesar el usuario.
14. En el método `configure(HttpSecurity http) throws Exception {}`  se establecen las rutas a las que puede acceder el usuario de acuerdo al role que se le asigno, tambien permite personalizar el **login** pues spring nos proporciona un de manera predeterminada.

## Imagenes de muestra 

### Continuara ...

[img-diagrama]:ruta/mer.png "Diagrama"
[img-entities]:ruta/relationship-entities.png "Relación de entidades"

