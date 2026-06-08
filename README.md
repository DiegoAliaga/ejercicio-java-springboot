# Ejercicio Java Springboot



### Dependencias

|| versión |
| ------------ | ------------ |
| Gradle | 8.14.5 |
| Java | 17 |
| Spring Boot | 3.5.14 |

#### Script Sql
Se ejecuta automaticamente al levantar el proyecto,

Base de Datos

La aplicación utiliza H2 en memoria para facilitar su ejecución y evaluación.

Las tablas generadas son:

USUARIO
TELEFONOS

La relación es:

USUARIO (1) ---- (N) TELEFONOS

La estructura SQL se encuentra en:

/resources/script.sql

### Ejecuta test
En la carpeta del proyecto abrir la consola y ejecutar el comando.

**gitbash**
```sh
./gradlew clean test
```


### Como probar

Para  poder  ejecutar el proyecto  se  debe  ir a la  carpeta ejercicio-java-springboot, Abrir  la  consola y ejecutar el comando.

**gitbash**
```sh
./gradlew bootRun
```

### Consola h2

http://localhost:8080/h2-console
```sh
JDBC URL: jdbc:h2:mem:usuariosdb
usuario: sa
contraseña: pass
```

### Swagger3

| | URL |
| ------------ | ------------ |
| Swagger UI | http://localhost:8080/swagger-ui/index.html |
| Apidocs| http://localhost:8080/v3/api-docs |


### Consideraciones

> Campo contraseña de request debe contener entre 4 a 8 caracteres consecutivos(mayusculas o minusculas) y
> 1 a 3 números para ser valido. Expresion regular modificable desde application.yml *app.validation.passwordRegex*.
> 
> Campo email de request debe tener formato example@example.cl. Expresion regular modificable desde application.yml *app.validation.emailRegex*
> 
> Campo numero en telefonos funciona bajo expresion regular, acepta solo 9 digitos. Expresion regular modificable desde application.yml *app.validation.telefonoRegex*

##### Crear Usuario request url

POST http://localhost:8080/api/v1.0/usuarios/crear

##### cURL

```sh
curl --location 'http://localhost:8080/api/v1.0/usuarios/crear' \
--header 'Content-Type: application/json' \
--data-raw '{
  "nombre": "Diego Aliaga",
  "email": "diegoaliaga@gmail.com",
  "password": "Qwerty12",
  "telefonos": [
    {
      "numero": "488503106",
      "codigoCiudad": "9",
      "codigoPais": "56"
    }
  ]
}'

```

##### response - 201

```json
{
  "id": "d0b8ad80-619a-4689-ab6d-6ef7039e7a99",
  "created": "2026-06-06T23:45:22.8945141",
  "modified": "2026-06-06T23:45:22.8945141",
  "lastLogin": "2026-06-06T23:45:22.8945141",
  "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJkaWVnb2FsaWFnYUBnbWFpbC5jb20iLCJpYXQiOjE3ODA4MDM5MjIsImV4cCI6MTc4MDg5MDMyMn0.0kHyK32fgTtOIIHDEPcHi7Xs3oLlHF1LaHUYquvNY2ZduJ9mYLetJPh_ZKOC1RA5",
  "active": true
}
```

##### Consultar Usuario request url

GET http://localhost:8080/api/v1.0/usuarios/consultar/{idUsuario}

##### cURL

```sh
curl --location 'http://localhost:8080/api/v1.0/usuarios/consultar/d0b8ad80-619a-4689-ab6d-6ef7039e7a99'
```

##### response - 200

```json
{
    "id": "d0b8ad80-619a-4689-ab6d-6ef7039e7a99",
    "nombre": "Diego Aliaga",
    "email": "diegoaliaga@gmail.com",
    "created": "2026-06-06T23:45:22.894514",
    "modified": "2026-06-06T23:45:22.894514",
    "lastLogin": "2026-06-06T23:45:22.894514",
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJkaWVnb2FsaWFnYUBnbWFpbC5jb20iLCJpYXQiOjE3ODA4MDM5MjIsImV4cCI6MTc4MDg5MDMyMn0.0kHyK32fgTtOIIHDEPcHi7Xs3oLlHF1LaHUYquvNY2ZduJ9mYLetJPh_ZKOC1RA5",
    "isActive": true,
    "telefonos": [
        {
            "numero": "488503106",
            "codigoCiudad": "9",
            "codigoPais": "56"
        }
    ]
}
```

