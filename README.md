# Bank API

Sistema bancario desarrollado con Java y Spring Boot que permite gestionar clientes, cuentas bancarias, depósitos, retiros, transferencias y autenticación segura mediante JWT.

## Tecnologías utilizadas

* Java 21
* Spring Boot 3.5.3
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* PostgreSQL
* Swagger / OpenAPI
* Docker
* Docker Compose
* Maven

## Características

### Gestión de Clientes

* Crear clientes
* Consultar clientes
* Buscar cliente por ID

### Gestión de Cuentas

* Crear cuentas bancarias
* Consultar cuentas
* Buscar cuenta por ID
* Buscar cuenta por número

### Operaciones Bancarias

* Depósitos
* Retiros
* Transferencias entre cuentas
* Historial de transacciones

### Seguridad

* Registro de usuarios
* Login
* JWT Authentication
* Roles USER y ADMIN
* Endpoints protegidos
* Respuestas personalizadas 401 y 403

---

## Ejecutar con Docker

### Requisitos

* Docker
* Docker Compose

### Levantar la aplicación

```bash
docker compose up --build
```

La aplicación estará disponible en:

http://localhost:8080

---

## Swagger

Documentación interactiva:

http://localhost:8080/swagger-ui/index.html

---

## Credenciales

Primero registra un usuario utilizando:

POST /api/auth/register

Ejemplo:

```json
{
  "username": "admin",
  "password": "123456"
}
```

Luego inicia sesión:

POST /api/auth/login

```json
{
  "username": "admin",
  "password": "123456"
}
```

Obtendrás un JWT:

```json
{
  "token": "eyJhbGciOi..."
}
```

Utiliza el token en los endpoints protegidos:

```text
Authorization: Bearer <TOKEN>
```

---

## Arquitectura

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

Seguridad:

```text
JWT Filter
    ↓
Spring Security
    ↓
Role Based Authorization
```

---

## Base de Datos

La base de datos PostgreSQL se crea automáticamente mediante Docker Compose.

Contenedores:

* bank-api
* bank-postgres

---

## Ejecutar localmente sin Docker

### Configurar PostgreSQL

Crear base de datos:

```sql
CREATE DATABASE bank_db;
```

### Ejecutar aplicación

```bash
./mvnw spring-boot:run
```

### Compilar

```bash
./mvnw clean package
```

### Ejecutar JAR

```bash
java -jar target/bank-api-0.0.1-SNAPSHOT.jar
```

---

## Autor

Jimmy Ortiz
