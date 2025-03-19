# Contenido

<!-- TOC -->
* [Contenido](#contenido)
* [Información](#información)
* [Dependencias](#dependencias)
* [Estructura](#estructura)
* [Utilización](#utilización)
<!-- TOC -->

# Información

- El servicio se ejecuta en el puerto: **9091** (application.properties)
- Acceder a la consola de [Swagger](http://localhost:9091/swagger.html)
- Acceder al cliente [h2](http://localhost:9091/h2-console) 
- - **user:** sa
- - **password:** qwerty
- - **JDBC URL:** jdbc:h2:mem:core

# Dependencias

Dependencias utilizadas
- JAVA = 19
- MAVEN = 3.8.5
- Spring Boot = 3.4.3

# Estructura

La aplicación sigue una arquitectura hexagonal/clean architecture, adaptada al estilo de Spring Boot:
- **Puertos**: Los repositorios y controladores actúan como puertos de entrada/salida.
- **Adaptadores**: Los controladores (REST) y repositorios (JPA) son adaptadores que conectan el núcleo de la aplicación con el mundo exterior.
- **Dominio**: Las entidades y los servicios forman el núcleo de la lógica de negocio.

```
src
├── main
│   ├── java
│   │   └── com.inditex.peoplecore
│   │       ├── PeopleCoreApplication.java // Clase principal de la aplicación.
│   │       ├── common
│   │       │   └── // Clases comunes del código.
│   │       ├── controller
│   │       │   └── // Controladores para acceder a funcionalidades.
│   │       ├── dto
│   │       │   └── // Clases DTOs.
│   │       ├── exception
│   │       │   └── // Clases para manejar Excepciones.
│   │       ├── mapper
│   │       │   └── // Clases para mapear Entidades a DTOs.
│   │       ├── repository
│   │       │   ├── // Interfaces de repositorios para acceder a entidades.
│   │       ├── entity
│   │       │   ├── // Entidades de base de datos.
│   │       │   └── pk
│   │       │       └── // Clases de llaves primarias compuestas.
│   │       └── service
│   │           └── // Clases para manejar funcionalidades .
│   └── resources
│       ├── application.properties // Archivo de propiedades.
│       └── db
│           ├── // Migraciones de base de datos.
└── test
├── java
│   └── com.inditex.peoplecore
│       └── // Clases de pruebas.
```

# Utilización
- Limpiar el proyecto
```shell
mvn clean
```
- Ejecutar pruebas
```shell
mvn test
```
- Compilar proyecto (incluidos tests) y generar archivo "jar".
```shell
mvn clean package
```