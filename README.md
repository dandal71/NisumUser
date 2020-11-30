# Nisum Gestión de Usuario

Este proyecto comprende un conjunto de servicios rest para la gestión de usuarios y sus teléfonos de contacto.

## Comenzando 🚀

Puedes obtener libremente una copia del mismo clonando el repositorio y abrirlo lcalmente con tu editor favorito.


### Pre-requisitos 📋

Debes tener Java jdk 1.8 + en tu entorno máquina local 



### Detalle de Endpoinst 🔧

Path: /create - Método: POST
Descripción: Creación de usuario: Permite crear un usuario
Body:
```json
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


```
Da un ejemplo
```

_Y repite_

```
hasta finalizar
```

_Finaliza con un ejemplo de cómo obtener datos del sistema o como usarlos para una pequeña demo_

## Ejecutando las pruebas ⚙️

_Explica como ejecutar las pruebas automatizadas para este sistema_

### Analice las pruebas end-to-end 🔩

_Explica que verifican estas pruebas y por qué_

```
Da un ejemplo
```

### Y las pruebas de estilo de codificación ⌨️

_Explica que verifican estas pruebas y por qué_

```
Da un ejemplo
```

## Despliegue 📦

_Agrega notas adicionales sobre como hacer deploy_

## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - El framework web usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [ROME](https://rometools.github.io/rome/) - Usado para generar RSS

## Contribuyendo 🖇️

Por favor lee el [CONTRIBUTING.md](https://gist.github.com/villanuevand/xxxxxx) para detalles de nuestro código de conducta, y el proceso para enviarnos pull requests.

## Wiki 📖

Puedes encontrar mucho más de cómo utilizar este proyecto en nuestra [Wiki](https://github.com/tu/proyecto/wiki)

## Versionado 📌

Usamos [SemVer](http://semver.org/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/tu/proyecto/tags).

## Autores ✒️

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Andrés Villanueva** - *Trabajo Inicial* - [villanuevand](https://github.com/villanuevand)
* **Fulanito Detal** - *Documentación* - [fulanitodetal](#fulanito-de-tal)

También puedes mirar la lista de todos los [contribuyentes](https://github.com/your/project/contributors) quíenes han participado en este proyecto. 

## Licencia 📄

Este proyecto está bajo la Licencia (Tu Licencia) - mira el archivo [LICENSE.md](LICENSE.md) para detalles

## Expresiones de Gratitud 🎁

* Comenta a otros sobre este proyecto 📢
* Invita una cerveza 🍺 o un café ☕ a alguien del equipo. 
* Da las gracias públicamente 🤓.
* etc.



---
⌨️ con ❤️ por [Villanuevand](https://github.com/Villanuevand) 😊
