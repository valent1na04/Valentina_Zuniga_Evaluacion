# Valentina_Zuniga_Evaluacion
Tarea evaluada 3 ingeniería de software

# 🛋️ Sistema de Gestión de Muebles – Spring Boot + MySQL + Docker Compose

Este proyecto es una API REST para gestionar **muebles, cotizaciones, variantes, ventas y detalles de cotización**.
Utiliza **Spring Boot**, **MySQL** y **Docker Compose**, e implementa arquitectura en capas con patrones de diseño para mantener un código limpio y escalable.

## Tecnologías Principales

* Java
* Spring Boot 3
* Spring Data JPA
* MySQL
* Docker & Docker Compose
* phpMyAdmin
* Lombok

---

## 🐳 Ejecución con Docker Compose

Clonar el proyecto y ejecutar:

```bash
docker-compose up --build
```

### Servicios levantados

| Servicio   | Puerto | Descripción               |
| ---------- | ------ | ------------------------- |
| Backend    | 8080   | API REST                  |
| MySQL      | 3306   | Base de datos             |
| phpMyAdmin | 8081   | UI para administrar la DB |

---

## 🗄️ Acceso a Base de Datos

* **phpMyAdmin:** [http://localhost:8081](http://localhost:8081)
* **Base:** `mysql`

**Credenciales**

* Usuario: `vale`
* Contraseña: `milongo`
* Root password: `example`

---

## 📚 Endpoints Principales

> La documentación completa está en el informe!!!

### **Muebles**

* GET `/api/muebles`
* GET `/api/muebles/{id}`
* POST `/api/muebles`
* PUT `/api/muebles/{id}`
* DELETE `/api/muebles/{id}`

### **Variantes**

* GET `/api/variantes`
* POST `/api/variantes`
* PUT `/api/variantes/{id}`
* DELETE `/api/variantes/{id}`

### **Cotizaciones**

* GET `/api/cotizaciones`
* POST `/api/cotizaciones`
* PUT `/api/cotizaciones/{id}`
* DELETE `/api/cotizaciones/{id}`

### **Ventas**

* GET `/api/ventas`
* POST `/api/ventas`
* PUT `/api/ventas/{id}`
* DELETE `/api/ventas/{id}`

### **Detalle Cotización**

* GET `/api/detalle-cotizacion`
* POST `/api/detalle-cotizacion`
* PUT `/api/detalle-cotizacion/{id}`
* DELETE `/api/detalle-cotizacion/{id}`

---

## 🧩 Patrones de Diseño Implementados

Detalles en el informe

* DTO (Data Transfer Objects)
* Repository Pattern
* Service Layer
* Controller Pattern
* Uso de Builder/Factory en entidades específicas
* Beans Singleton por configuración de Spring

---

## 🧪 Testing (Unit Tests)

✔️ Resumen de Testing

Total de tests: 77
Frameworks: JUnit 5 + Mockito

Cobertura:
* Servicios
* Controladores
* Mappers
* Strategy Pattern

Todos los tests pasan correctamente

✔️ Ejecutar todos los tests
```bash
./mvnw test
```

✔️ Ejecutar un test específico
```bash
./mvnw test -Dtest=MuebleServiceTest
```

✔️ Organización de los tests
```bash
src/test/java/cl/ubiobio/tareita/
├── controllers/
├── mappers/
├── services/
└── strategy/
```

Los tests siguen el patrón AAA (Arrange – Act – Assert), usan mocks para independizar capas y validan tanto casos normales como excepciones y edge cases.

> 📦 Ejecución del Proyecto (sin Docker): 
> ./mvnw spring-boot:run
