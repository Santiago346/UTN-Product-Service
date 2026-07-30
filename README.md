# Product Service

Microservicio encargado de gestionar los productos bancarios (cuentas, seguros, plazos fijos, etc.) asociados a los clientes. Forma parte de una arquitectura de microservicios junto con `customer-service`, coordinados por un Eureka Server y un Config Server.

## Stack

- Spring Boot
- Spring Data JPA + H2 (base de datos en memoria)
- Spring Cloud Config Client
- Spring Cloud Netflix Eureka Client

## Configuración

La configuración de puerto, datasource y Eureka se obtiene del **Config Server** (archivo `product-service.yaml` en `config-repo/`). El `application.yaml` local solo define lo mínimo:

```yaml
spring:
  application:
    name: product-service
  config:
    import: optional:configserver:http://localhost:8888
```

## Modelo de datos

`Product`: id, clienteId, tipo (enum `ProductType`), nombre, descripcion, montoAsociado, tasaInteres, activo, fechaInicio, fechaVencimiento, fechaAlta.

## Endpoints

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/productos` | Lista todos los productos |
| POST | `/productos` | Crea un producto nuevo |
| GET | `/productos/cliente/{clienteId}` | Lista los productos de un cliente puntual |

### Ejemplo — POST /productos

```json
{
  "clienteId": 1,
  "tipo": "CUENTA_CORRIENTE",
  "nombre": "Caja de Ahorro",
  "descripcion": "Cuenta simple",
  "montoAsociado": 100000,
  "tasaInteres": 0
}
```

## Cómo correrlo

Requiere que **Eureka Server** y **Config Server** estén corriendo previamente.

```bash
mvn spring-boot:run
```

El servicio levanta en el puerto **8082** (definido en el Config Server) y se registra automáticamente en Eureka.

## Datos de prueba

El proyecto incluye un `DataLoader` (`CommandLineRunner`) que precarga productos de ejemplo en la base H2 al arrancar, asociados a los `clienteId` 1 y 2.