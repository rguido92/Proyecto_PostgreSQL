Proyecto de Gestión de Base de Datos de Fútbol

En este proyecto se pide crear una base de datos PostgreSQL denominada futbol y un programa Java que permita manejar y gestionar dicha base de datos utilizando Hibernate.
Descripción del Proyecto

El objetivo principal es gestionar una base de datos de fútbol, incluyendo jugadores, equipos y partidos. El modelo relacional de la base de datos se describe a continuación.
Entidades y Relaciones

    Jugadores
        jugador_id: tipo serial y clave primaria
        datos_personales: de tipo Persona
        jugador_info: de tipo Jugador

    Equipos
        equipo_id: tipo serial y clave primaria
        equipo_info: de tipo Equipo

    Partidos
        partido_id: tipo serial y clave primaria
        fecha: tipo fecha
        Relación varios a 1 con Equipos (equipo_local)
        Relación varios a 1 con Equipos (equipo_visitante)

Objetos

    Persona
        nombre: tipo varchar
        edad: tipo entero

    Jugador
        dorsal: tipo entero
        posicion: tipo varchar
        altura: tipo decimal

    Equipo
        nombre: tipo varchar
        ciudad: tipo varchar
        entrenador: tipo Persona
Estructura del Proyecto
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── proyecto
│   │   │           ├── dao
│   │   │           │   ├── EquipoDao.java
│   │   │           │   ├── JugadorDao.java
│   │   │           │   └── PartidoDao.java
│   │   │           ├── model
│   │   │           │   ├── Equipo.java
│   │   │           │   ├── Jugador.java
│   │   │           │   ├── Partido.java
│   │   │           │   └── Persona.java
│   │   │           ├── service
│   │   │           │   ├── EquipoService.java
│   │   │           │   ├── JugadorService.java
│   │   │           │   └── PartidoService.java
│   │   │           ├── util
│   │   │           │   └── HibernateUtil.java
│   │   │           └── Main.java
│   └── resources
│       ├── hibernate.cfg.xml
│       └── log4j.properties
└── pom.xml

Descripción de los Componentes
DAO

    EquipoDao.java: DAO para las operaciones CRUD de Equipo.
    JugadorDao.java: DAO para las operaciones CRUD de Jugador.
    PartidoDao.java: DAO para las operaciones CRUD de Partido.

Modelo

    Equipo.java: Clase que representa a un equipo.
    Jugador.java: Clase que representa a un jugador.
    Partido.java: Clase que representa a un partido.
    Persona.java: Clase que representa a una persona.

Servicios

    EquipoService.java: Servicio para las operaciones de negocio relacionadas con los equipos.
    JugadorService.java: Servicio para las operaciones de negocio relacionadas con los jugadores.
    PartidoService.java: Servicio para las operaciones de negocio relacionadas con los partidos.

Utilidades

    HibernateUtil.java: Clase de utilidad para manejar la configuración de Hibernate.

Main

    Main.java: Clase principal que inicia la aplicación.

Herramientas Utilizadas

    Hibernate: Para la capa de persistencia.
    PostgreSQL: Como sistema de gestión de bases de datos.
    Java: Para la implementación de la lógica de negocio y la interacción con la base de datos.
    Maven: Para la gestión del proyecto y sus dependencias.
    Log4j: Para el manejo de logs en la aplicación.
