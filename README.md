- Para probar en postman
feature/profiles-managment
-  [Get]http://localhost:8080/api/v1/farm/location/nice : para buscar por location
- [Get] http://localhost:8080/api/v1/farm/1 : para buscar por id
- [POST] http://localhost:8080/api/v1/farm Para enviar un json
```

    {
  "farmName": "Lima",
  "location": "nice",
  "type": "Pollo",
  "infrastructure": "Avicola",
  "services": "ningunoc",
  "status": "Pollo",
  "certificates": "Tsuneo",
  "image": "asd"
  }
```
  
    - [PUT] http://localhost:8080/api/v1/farm/1 Para ACTUALIZAR un json
-es necesario poner el id

Comando para insertar informacion desde MySql:
```
INSERT INTO farms (farm_name, location, type, infrastructure, services, status, certificates, image)
VALUES ('Lima', 'nice', 'Pollo', 'Avicola', 'ningunoc', 'Pollo', 'Tsuneo', 'asd');
```


```
    
  - {
    "id": 1,
    "farmName": "BARRANCA",
    "location": "BARRANCA",
    "type": "Pollo",
    "infrastructure": "Avicola",
    "services": "ningunoc",
    "status": "Pollo",
    "certificates": "Tsuneo"
    }
```
  - 
  - CREAR PROFILE 
```
{
        "id": 2,
        "firstName": "R Doe",
        "lastName":"xd",
        "direction": "123 Calle Principal",
        "phone": "1234567890",
        "gender": "Male",
        "birthDate": "1990-01-01",
        "documentNumber": "123456789",
        "documentType": "DNI",
        "role": "Admin"
    }
```
-Response of All
- Se esconde el firstName y lastName por el FullName eso es porque se creo el value Object xD :c
```
[
{ 
    "id": 1,
    "fullName": "John Doe",
    "direction": "123 Calle Principal",
    "phone": "1234567890",
    "gender": "Male",
    "birthDate": "1990-01-01",
    "documentNumber": "123456789",
    "documentType": "DNI",
    "role": "Admin"
}
}
]

```
crear subscription
````
{
"firstName": "rODRI",
"lastName": "string",
"direction": "string",
"phone": "string",
"gender": "string",
"birthDate": "string",
"documentNumber": "string",
"documentType": "string",
"role": "string",
"price": 0,
"description": "string",
"paid": true
}
```` 


LINK PARA VER JSONS
http://localhost:8080/swagger-ui/index.html#/

### Tabla Subscription
-  [Get]http://localhost:8080/api/v1/subscription/all : para buscar todos
-  [Get]http://localhost:8080/api/v1/subscription/1 : para buscar por id

### Tabla Socials
-  [Get]http://localhost:8080/api/v1/socials/all : para buscar todos
-  [Get]http://localhost:8080/api/v1/socials/1 : para buscar por id