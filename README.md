# Foro Hub - Challenge back end

### _Foro de tópicos_

## Objetivo:  
 Api para gestionar tópicos,permite a los usuarios:

 *   Crear un nuevo tópico
 *   Mostrar todos los tópicos creados
 *   Mostrar un tópico específico
 *   Actualizar un tópico
 *   Eliminar un tópico
 
## Entorno de desarrollo utilizado:

* Java (versión 17)
* Maven: versión 4 en adelante
* Spring: versión 3.3.6
* mysql  Ver 8.0.40-0ubuntu0.22.04.1 for Linux
* IDE IntelliJ IDEA -
 
__Configuración al crear el proyecto en Spring Initializr:__

* Java (versión 17 en adelante)
* Maven (Initializr utiliza la versión 4)
* Spring Boot (versión 3.3.6)
* Proyecto en JAR
    
__Dependencias para agregar al crear el proyecto en Spring Initializr:__
* spring-boot-starter-web
* spring-boot-devtools
* lombok 1.18.4
* spring-boot-starter-data-jpa
* flyway-mysql
* flyway-core
* mysql-connector-j
* jackson-databind 2.16.0
* spring-boot-starter-validation
* spring-boot-starter-security
* com.auth0 , java-jwt 4.2.0
* Insomnia 10.3.0

## Interacción con el usuario:
* Acepta :
* Login de usuario : POST en http://localhost:8080/login : 
  Devuelve JWT requerido para autenticar demás recursos
* Registrar tópico : POST en http://localhost:8080/topicos
* Listar tópicos : GET en http://localhost:8080/topicos?page={pag}&size={items}
* Detalle tópico: GET en http://localhost:8080/topicos/Id 
* Actualizar tópico: PUT en http://localhost:8080/topicos
* Borrar tópico: DELETE en http://localhost:8080/topicos/{Id}

## Aspectos del proyecto:
* Se utilizaron variables de entorno para el acceso a base de datos
* Todos los campos son obligatorios para registrar o actualizar tópico.
* La API no permite el registro ni actualización de tópicos duplicados (con el mismo título y mensaje).
* Al registrar o actualizar se inserta fecta y hora en el registro.
* Token con algoritmo HMAC256 , contraseña secreta , expiración dos horas tras ser generado.
Descripción
Diagrama de Base de Datos

## Base de datos para almacenar la información de la aplicación.

Para crear un tópico se necesitan las siguientes informaciones:

* id, título, mensaje, fecha de creación, status (estado del tópico), autor, curso

Usuarios necesitan las siguientes informaciones:

* Login Id , clave ( ByCrypt password HASH almacenado en tabla )
    