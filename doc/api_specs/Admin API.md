# Admin API

## Get Admin By AdminId
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
