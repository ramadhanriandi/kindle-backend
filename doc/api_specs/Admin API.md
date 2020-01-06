# Admin API

## Get Admin by AdminId
- Endpoint : `/kindle-backend/api/admins/{adminId}`
- HTTP Method : `GET`
- Path Variable :
	- `adminId` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code" : 200,
	"status" : "OK",
	"data": [{
		"id" : 1,
		"email" : "admin@admin.com",
		"username" : "admin",
		"password" : "admin"
	}]
}
```

## Update Admin
- Endpoint : `/kindle-backend/api/admins/{adminId}`
- HTTP Method : `PUT`
- Path Variable :
	- `adminId` : Integer
- Request Header :
	- Accept : `application/json`
- Request Body :
    - `email` : String
    - `username`: String
    - `password` : String
    - `status` : String, default is `"Active"` 
- Response Body (Success) :
```
{
	"code" : 200,
	"status" : "OK",
	"data": [{
		"id" : 1,
		"message" : "Login success"
	}]
}
```

## Login Admin
- Endpoint : `/kindle-backend/api/admins/login`
- HTTP Method : `PUT`
- Path Variable :
	- `adminId` : Integer
- Request Header :
	- Accept : `application/json`
- Request Body :
    - `email` : String
    - `password` : String
- Response Body (Success) :
```
{
	"code" : 200,
	"status" : "OK",
	"data": [{
		"id" : 1,
		"message" : "Login success"
	}]
}
```
