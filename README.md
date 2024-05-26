- Para probar en postman
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
  "certificates": "Tsuneo"
  }
```
  
    - [PUT] http://localhost:8080/api/v1/farm/1 Para ACTUALIZAR un json
-es necesario poner el id
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
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "direction": "123 Main St",
    "phone": "+1234567890",
    "gender": "Male",
    "birthDate": "1990-01-01",
    "documentNumber": "A12345678",
    "documentType": "Passport",
    "role": "Admin"
}
```
-Response of All
```

[
{
"id": 1,
"email": "Tsuneo",
"password": "Lima",
"profile": {
"id": 1,
"firstName": "John",
"lastName": "Doe",
"direction": "123 Main St",
"phone": "+1234567890",
"gender": "Male",
"birthDate": "1990-01-01",
"documentNumber": "A12345678",
"documentType": "Passport",
"role": "Admin"
}
}
]
```
