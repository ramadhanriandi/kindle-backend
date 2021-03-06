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
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "admin",
		"attributes": {
			"email": "admin@admin.com",
			"username": "admin",
			"password": "admin"
        	}
	}]
}
```
- Response Body (Fail) :
```
{
	"code": 400,
	"status": "Bad Request",
	"errors": [{
		"status": 404,
		"detail": "AdminId not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch admin data"
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
    - `username` : String
    - `password` : String
    - `status` : String, default is `"Active"` 
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "admin"
	}]
}
```
- Response Body (Fail) :
```
{
	"code": 400,
	"status": "Bad Request",
	"errors": [{
		"status": 404,
		"detail": "AdminId not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot update admin data"
	}]
}
```

## Login Admin
- Endpoint : `/kindle-backend/api/admins/login`
- HTTP Method : `POST`
- Request Header :
	- Accept : `application/json`
- Request Body :
    - `email` : String
    - `password` : String
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "admin"
	}]
}
```
- Response Body (Fail) :
```
{
	"code": 400,
	"status": "Bad Request",
	"errors": [{
		"status": 404,
		"detail": "Wrong email or password"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch admin data"
	}]
}
```
