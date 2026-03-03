# ⚽ Gestión de Base de Datos de Fútbol — Java + Hibernate + PostgreSQL

Aplicación backend en **Java** para gestionar una base de datos de fútbol: jugadores, equipos y partidos. Implementa la capa de persistencia con **Hibernate/JPA** sobre **PostgreSQL**, siguiendo la arquitectura **DAO + Service**.

> 🎯 Proyecto de la asignatura **Acceso a Datos** — CFGS DAM · IES Teis 2022–2024

---

## ✨ Funcionalidades

- 🏃 CRUD completo de **jugadores** (datos personales + datos deportivos)
- 🏟️ CRUD completo de **equipos** (nombre, ciudad, entrenador)
- 📅 Gestión de **partidos**: equipo local, visitante y fecha
- 🔗 Relaciones entre entidades gestionadas por Hibernate (ManyToOne, OneToOne, Embedded)
- 📝 Logging con **Log4j**

---

## 🛠️ Stack tecnológico

| Capa | Tecnología |
|------|-----------|
| Lenguaje | Java 17 |
| ORM / Persistencia | Hibernate 6 / JPA |
| Base de datos | PostgreSQL |
| Logging | Log4j |
| Build | Maven |
| Config Hibernate | hibernate.cfg.xml |

---

## 🗄️ Modelo de datos

```
Jugador (@Entity)
├── jugador_id          PK serial
├── datos_personales    @Embedded → Persona (nombre, edad)
└── jugador_info        @Embedded → JugadorInfo (dorsal, posicion, altura)

Equipo (@Entity)
├── equipo_id           PK serial
└── equipo_info         @Embedded → EquipoInfo (nombre, ciudad, entrenador→Persona)

Partido (@Entity)
├── partido_id          PK serial
├── fecha               Date
├── equipo_local        @ManyToOne → Equipo
└── equipo_visitante    @ManyToOne → Equipo
```

---

## 📁 Estructura del proyecto

```
Proyecto_PostgreSQL/
└── Proyecto_posgresql-main/
    └── src/
        ├── main/
        │   ├── java/com/proyecto/
        │   │   ├── dao/
        │   │   │   ├── EquipoDao.java       ← CRUD Equipo via Hibernate
        │   │   │   ├── JugadorDao.java      ← CRUD Jugador via Hibernate
        │   │   │   └── PartidoDao.java      ← CRUD Partido via Hibernate
        │   │   ├── model/
        │   │   │   ├── Equipo.java
        │   │   │   ├── Jugador.java
        │   │   │   ├── Partido.java
        │   │   │   └── Persona.java         ← Embeddable
        │   │   ├── service/
        │   │   │   ├── EquipoService.java
        │   │   │   ├── JugadorService.java
        │   │   │   └── PartidoService.java
        │   │   ├── util/
        │   │   │   └── HibernateUtil.java   ← SessionFactory singleton
        │   │   └── Main.java
        │   └── resources/
        │       ├── hibernate.cfg.xml        ← Config conexión + dialect
        │       └── log4j.properties
        └── pom.xml
```

---

## 🚀 Cómo ejecutar

### Requisitos
- Java 17+
- PostgreSQL 14+
- Maven

```bash
# 1. Clonar el repositorio
git clone https://github.com/rguido92/Proyecto_PostgreSQL.git
cd Proyecto_PostgreSQL/Proyecto_posgresql-main

# 2. Crear la base de datos
psql -U postgres -c "CREATE DATABASE futbol;"

# 3. Configurar hibernate.cfg.xml
# <property name="connection.url">jdbc:postgresql://localhost:5432/futbol</property>
# <property name="connection.username">tu_usuario</property>
# <property name="connection.password">tu_password</property>
# <property name="hbm2ddl.auto">update</property>   ← crea tablas automáticamente

# 4. Compilar y ejecutar
mvn compile
mvn exec:java -Dexec.mainClass="com.proyecto.Main"
```

Hibernate creará el esquema automáticamente en el primer arranque (`hbm2ddl.auto=update`).

---

## 🧪 Tests

```bash
mvn test
```

---

## 💡 Conceptos aplicados

- **Hibernate ORM**: mapeo de entidades con anotaciones JPA (`@Entity`, `@Table`, `@Column`, `@ManyToOne`, `@Embedded`)
- **SessionFactory**: gestión del ciclo de vida de sesiones Hibernate
- **Patrón DAO + Service**: separación entre persistencia y lógica de negocio
- **Objetos embebidos**: reutilización de `Persona` como `@Embeddable` en Jugador y Equipo
- **Log4j**: trazabilidad de operaciones sobre la base de datos

---

## 👤 Autor

**Rodrigo Guido** — Desarrollador Backend Java  
[LinkedIn](https://www.linkedin.com/in/rodrigo-guido92) · [GitHub](https://github.com/rguido92) · rodrigoguidoarias@gmail.com
