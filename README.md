# 🚗 ProyectoGestionTaller - AutoService Pro

Aplicación web para la gestión integral de un taller de automóviles, desarrollada con **Spring Boot** y **Thymeleaf**. 

## 📋 Descripción

**AutoService Pro** es un sistema de gestión diseñado para talleres mecánicos que permite administrar clientes, vehículos y facturas de manera eficiente. La aplicación ofrece una interfaz intuitiva basada en Bootstrap 5 para facilitar las operaciones diarias del taller.

## ✨ Características Principales

### 🧑‍💼 Gestión de Clientes
- Registro completo de clientes con DNI, nombre y teléfono
- Búsqueda y filtrado de clientes
- Validación de datos con restricciones personalizadas
- Visualización del total facturado por cliente

### 🚙 Gestión de Vehículos
- Registro de coches con matrícula, marca, modelo y año
- Búsqueda de vehículos por matrícula
- Asociación de vehículos con sus propietarios
- Listado completo del parque móvil

### 🧾 Gestión de Facturas
- Creación de facturas con descripción detallada del trabajo
- Registro de piezas cambiadas o reparadas
- Cálculo automático de IVA (21%)
- Asociación de facturas con clientes y vehículos
- Visualización de ingresos totales y medias
- Control de fechas de las reparaciones

### 📊 Estadísticas y Reportes
- Cálculo de ingresos totales del taller
- Media de ingresos por factura
- Total facturado por cliente
- Historial de reparaciones por vehículo

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3. 5.6**
  - Spring Data JPA
  - Spring Web
  - Spring Validation
- **Lombok** - Reducción de código boilerplate
- **H2 Database** - Base de datos en memoria para desarrollo

### Frontend
- **Thymeleaf** - Motor de plantillas
- **Bootstrap 5. 3. 2** - Framework CSS
- **Bootstrap Icons** - Iconografía

### Build Tool
- **Maven** - Gestión de dependencias y construcción del proyecto

## 📁 Estructura del Proyecto

```
casadobayonantoniojesus/
├── src/
│   ├── main/
│   │   ├── java/com/salesianostriana/dam/casadobayonantoniojesus/
│   │   │   ├── controller/
│   │   │   │   ├── ClienteController.java
│   │   │   │   ├── CocheController.java
│   │   │   │   └── FacturaController.java
│   │   │   ├── model/
│   │   │   │   ├── Cliente.java
│   │   │   │   ├── Coche. java
│   │   │   │   └── Factura. java
│   │   │   ├── repository/
│   │   │   │   ├── IClienteRepository.java
│   │   │   │   ├── ICocheRepository.java
│   │   │   │   └── IFacturaRepository.java
│   │   │   ├── service/
│   │   │   │   ├── ClienteService.java
│   │   │   │   ├── CocheService.java
│   │   │   │   └── FacturaService.java
│   │   │   └── CasadobayonantoniojesusApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── clientes. html
│   │       │   ├── coches.html
│   │       │   ├── facturas.html
│   │       │   ├── formularioCliente.html
│   │       │   ├── formularioCoche.html
│   │       │   ├── formularioFactura.html
│   │       │   └── ClienteTotalFacturado.html
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## 🚀 Instalación y Ejecución

### Prerrequisitos
- Java JDK 21 o superior
- Maven 3. 6+

### Pasos de instalación

1. **Clonar el repositorio**
```bash
git clone https://github. com/ajcasadob/ProyectoGestionTaller.git
cd ProyectoGestionTaller/casadobayonantoniojesus
```

2. **Compilar el proyecto**
```bash
mvn clean install
```

3. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

4. **Acceder a la aplicación**
```
http://localhost:8080
```

## 💾 Base de Datos

El proyecto utiliza **H2 Database** en modo embebido para desarrollo, lo que permite:
- Inicio rápido sin configuración adicional
- Persistencia en memoria durante la ejecución
- Consola web para inspección de datos

### Acceso a la consola H2
```
http://localhost:8080/h2-console
```

## 📐 Modelo de Datos

### Cliente
- `id` (Long) - Identificador único
- `dni` (String) - DNI del cliente
- `nombre` (String) - Nombre completo
- `telefono` (String) - Número de teléfono

### Coche
- `id` (Long) - Identificador único
- `matricula` (String) - Matrícula del vehículo
- `marca` (String) - Marca del coche
- `modelo` (String) - Modelo del vehículo
- `cliente` (Cliente) - Propietario del vehículo

### Factura
- `id` (Long) - Identificador único
- `descripcion` (String) - Descripción del trabajo
- `precio` (Double) - Precio sin IVA
- `pieza` (String) - Pieza cambiada/reparada
- `fecha` (LocalDateTime) - Fecha de la reparación
- `cliente` (Cliente) - Cliente asociado
- `coche` (Coche) - Vehículo reparado

## 🧮 Funcionalidades del Sistema

### Cálculos Automáticos
- **IVA**: Aplicación automática del 21% sobre el precio base
- **Precio con IVA**: Precio base + IVA
- **Ingresos totales**: Suma de todas las facturas
- **Media de ingresos**: Promedio de facturación

### Validaciones
- DNI: Formato español (8 dígitos + letra)
- Precios: No negativos
- Fechas: No posteriores a la fecha actual
- Campos obligatorios: Validación en cliente y servidor

## 🎨 Interfaz de Usuario

La interfaz cuenta con:
- **Diseño responsive** adaptado a móviles y tablets
- **Navegación lateral** con iconos descriptivos
- **Tarjetas informativas** con estadísticas en tiempo real
- **Tablas interactivas** para visualización de datos
- **Formularios validados** con feedback visual
- **Sistema de colores** profesional y accesible

## 👨‍💻 Autor

**Antonio Jesús Casado Bayón**

## 📄 Licencia

Este proyecto es un trabajo académico desarrollado para el ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM) en Salesianos Triana.

## 🔮 Mejoras Futuras

- [ ] Implementación de sistema de autenticación
- [ ] Exportación de facturas a PDF
- [ ] Gestión de citas y calendario
- [ ] Inventario de piezas
- [ ] Notificaciones por email/SMS
- [ ] Dashboard con gráficos estadísticos
- [ ] API REST para integración con otros sistemas
- [ ] Base de datos PostgreSQL para producción

---

⭐ Si te ha resultado útil este proyecto, considera darle una estrella en GitHub
