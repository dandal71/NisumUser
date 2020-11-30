# Nisum Gestión de Usuario

Este proyecto comprende un conjunto de servicios rest para la gestión de usuarios y sus teléfonos de contacto.

## Comenzando 🚀

Puedes obtener libremente una copia del mismo clonando el repositorio y abrirlo lcalmente con tu editor favorito.


### Pre-requisitos 📋

Debes tener Java jdk 1.8 + en tu entorno máquina local 



### Endpoint /create 🔧

Path: /create - Método: POST
Descripción: Creación de usuario: Permite crear un usuario
Login: No requiere

```json
Body:
{        
    "name": "Daniel D. Perez",
    "username": "ddperez",
    "email": "dandal71@gmail.cl",  
    "password": "Dd123456",    
    "phones": [
        {
           "number":"22223366",
            "cityCode": "0341",
            "countryCode": "54"
        },
        {
            "number":  "50501144",
            "cityCode": "0341",
            "countryCode": "54"
        }
    ]  
}

Respuesta:
{
    "timestampResponse": "29-11-2020 11:25:27",
    "status": "OK",
    "message": "Usuario creado con éxito",
    "user": {
        "id": "65c68db2-e66b-4837-896c-2eeb4c934cd2",
        "name": "Daniel D. Dalmagro",
        "username": "ddalmagro",
        "password": "$2a$10$DLWb6gMpv8W0CpxKxIgJ1OvYmh90t5qiQWsSuZyfLatQ.SSwS8yLa",
        "email": "dandal71@gmail.cl",
        "token": null,
        "status": 1,
        "created": "2020-11-29T23:25:27.1949137",
        "modified": "2020-11-29T23:25:27.1949137",
        "lastLogin": "2020-11-29T23:25:27.1949137",
        "phones": [
            {
                "id": "d6e0d38b-c533-43fd-a1a6-250435844915",
                "number": "22223366",
                "cityCode": "0341",
                "countryCode": "54"
            },
            {
                "id": "4578911b-6588-4d73-80ae-8666a1be6d46",
                "number": "50501144",
                "cityCode": "0341",
                "countryCode": "54"
            }
        ]
    },
    "users": null,
    "errors": null
}

```


### Endpoint /login 🔧
Path: /login - Method: Post - Body: x-www-form-urlencoded 
Campos form: 
username: nombre de usuario para el login
passowrd: contraseña ingreso 

Respuesta éxito:
```json
{
    "timestampResponse": "29-11-2020 11:36:29",
    "status": "OK",
    "message": "Login Exitoso",
    "user": {
        "id": "65c68db2-e66b-4837-896c-2eeb4c934cd2",
        "name": "Daniel D. Dalmagro",
        "username": "ddalmagro",
        "password": "$2a$10$YJnP26zNr..9n7W5h/aq1.sp.89Op.y4XAdkDeXTFFjUDnszCdSoO",
        "email": "dandal71@gmail.cl",
        "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJuaXN1bUpXVCIsInN1YiI6ImRkYWxtYWdybyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MDY3MDM3ODksImV4cCI6MTYwNjcwNDM4OX0.RZnEi8t6sRWnOb1a6ofK1vUohQzzKSPB1MgZSsho_jWmsq4kPW_QDfdUlbfJIdwx-UgRexuvBUP-NYtxpmaegg",
        "status": 1,
        "created": "2020-11-29T23:25:27.194914",
        "modified": "2020-11-29T23:36:29.2555956",
        "lastLogin": "2020-11-29T23:36:29.148568",
        "phones": []
    },
    "users": null,
    "errors": null
}

```

### Endpoint /list 🔧
Path: api/v1/user/private/list - Método: GET
Requiere login previo - Enviar token.
```json
Respuesta
{
    "timestampResponse": "29-11-2020 11:39:42",
    "status": "OK",
    "message": "Entidades obtenidas éxitosamente",
    "user": null,
    "users": [
        {
            "id": "65c68db2-e66b-4837-896c-2eeb4c934cd2",
            "name": "Daniel D. Dalmagro",
            "username": "ddalmagro",
            "password": "$2a$10$YJnP26zNr..9n7W5h/aq1.sp.89Op.y4XAdkDeXTFFjUDnszCdSoO",
            "email": "dandal71@gmail.cl",
            "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJuaXN1bUpXVCIsInN1YiI6ImRkYWxtYWdybyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MDY3MDM3ODksImV4cCI6MTYwNjcwNDM4OX0.RZnEi8t6sRWnOb1a6ofK1vUohQzzKSPB1MgZSsho_jWmsq4kPW_QDfdUlbfJIdwx-UgRexuvBUP-NYtxpmaegg",
            "status": 1,
            "created": "2020-11-29T23:25:27.194914",
            "modified": "2020-11-29T23:36:29.255596",
            "lastLogin": "2020-11-29T23:36:29.148568",
            "phones": []
        }
    ],
    "errors": null
}
```


### Endpoint /get 🔧
Path: api/v1/user/private/get/{id} - Método: GET
Requiere login previo - Enviar token.

```json
Respuesta
{
    "timestampResponse": "29-11-2020 11:39:42",
    "status": "OK",
    "message": "Entidades obtenidas éxitosamente",
    "user": null,
    "users": [
        {
            "id": "65c68db2-e66b-4837-896c-2eeb4c934cd2",
            "name": "Daniel D. Dalmagro",
            "username": "ddalmagro",
            "password": "$2a$10$YJnP26zNr..9n7W5h/aq1.sp.89Op.y4XAdkDeXTFFjUDnszCdSoO",
            "email": "dandal71@gmail.cl",
            "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJuaXN1bUpXVCIsInN1YiI6ImRkYWxtYWdybyIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MDY3MDM3ODksImV4cCI6MTYwNjcwNDM4OX0.RZnEi8t6sRWnOb1a6ofK1vUohQzzKSPB1MgZSsho_jWmsq4kPW_QDfdUlbfJIdwx-UgRexuvBUP-NYtxpmaegg",
            "status": 1,
            "created": "2020-11-29T23:25:27.194914",
            "modified": "2020-11-29T23:36:29.255596",
            "lastLogin": "2020-11-29T23:36:29.148568",
            "phones": []
        }
    ],
    "errors": null
}
```


## Construido con 🛠️

El proyecto fue creado con Spring Tool versión 4.0.1
Se utilizaron los siguientes framework y herramientas
* [Spring Boot] 
* [Spring Data JPA]
* [Spring Security]
* [Spring Web]

* [Gradle] Manejador de dependencias

## Autores ✒️

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Daniel E. Dalmagro** - *Trabajo Inicial* - [villanuevand](https://github.com/dandal71)

## Licencia 📄

Este proyecto está bajo la Licencia (Tu Licencia) - mira el archivo [LICENSE.md](LICENSE.md) para detalles

---
⌨️ con ❤️ por [Daniel E. Dalmagro(https://github.com/dandal71) 😊
