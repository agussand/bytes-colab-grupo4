# FeatureFlag API

**Tipo:** Backend para gestión de flags de funcionalidades en entornos reales  

**Descripción breve:**  
Desarrollar una API REST que permita crear, activar o desactivar funcionalidades (*feature flags*) de manera dinámica según entornos (dev, staging, prod) o clientes específicos. Este tipo de sistemas es usado en empresas reales para desplegar código sin liberar funciones, hacer pruebas A/B o activar nuevas features por etapas.

---

## 🛠️ Tecnologías y herramientas

- **Lenguaje:** Java  
- **Framework:** Spring Boot (MVC)  
- **Base de datos:** PostgreSQL  
- **Seguridad y autenticación:** Spring Security + JWT  
- **Documentación de APIs:** Swagger UI  
- **Testing:** Postman (funcional) + JUnit/Mockito (opcional)  
- **Control de versiones:** Git + GitHub  
- **Gestión del proyecto:** Trello (opcional)  

---

## 🎯 Objetivo General

Construir un backend que permita:  
- Crear funcionalidades (*features*) con nombre, descripción y estado (on/off).  
- Activarlas o desactivarlas por entorno (dev, staging, prod) o por cliente.  
- Consultar qué funcionalidades están activas según el entorno o cliente.  

---

## ✅ Requisitos funcionales

### 1. Gestión de usuarios (autenticación)
- **POST** `/api/auth/register` — Registro de usuario con rol USER.  
- **POST** `/api/auth/login` — Login y entrega de JWT.  
- Todos los endpoints (salvo los de `auth`) deben requerir autenticación.  

### 2. Gestión de funcionalidades (features)
- **Crear nueva feature** → **POST** `/api/features`  
  - Campos → `name`, `description`, `enabledByDefault`  
- **Listar todas las features existentes** → **GET** `/api/features`  
- **Obtener detalle de una feature** → **GET** `/api/features/{id}`  

### 3. Activación y personalización por entorno o cliente
- **Activar una feature** para un entorno o cliente → **POST** `/api/features/{id}/enable`  
  - Cuerpo → `{ "environment": "dev", "clientId": "acme123" }`  
- **Desactivar una feature** para un entorno o cliente → **POST** `/api/features/{id}/disable`  
- **Consultar si una feature está activa** → **GET** `/api/features/check?feature=dark_mode&clientId=acme123&env=staging`  

---

## 🔐 Seguridad

- Uso de Spring Security + JWT.  
- Protección de rutas mediante filtros y anotaciones.  
- Solo usuarios autenticados pueden consultar y modificar features.  

---

## 🧠 Lógica de activación

Al consultar si una feature está activa para un entorno y cliente, el sistema debe verificar:  
1. Si existe una configuración específica para ese cliente.  
2. Si existe una configuración por entorno.  
3. Si no hay configuración específica, usar `enabledByDefault`.  

---

## 🧱 Modelo de datos

```java
// Feature.java
UUID id;
String name;
String description;
Boolean enabledByDefault;
List<FeatureConfig> configs;

// FeatureConfig.java
UUID id;
Environment environment; // Enum: DEV, STAGING, PROD
String clientId; // opcional
Boolean enabled;
Feature feature;
