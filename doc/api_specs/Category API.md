# Category API

## Get All Category
- Endpoint : `/kindle-backend/api/categories`
- HTTP Method : `GET`
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "category",
		"attributes": {
			"name": "Fiction"
    		}
	}, {
		"id": 2,
		"type": "category",
		"attributes": {
			"name": "Drama"
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
		"detail": "Category not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch the category"
	}]
}
```

## Get Category by CategoryId
- Endpoint : `/kindle-backend/api/categories/{categoryId}`
- HTTP Method : `GET`
- Path Variable :
	- `categoryId` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "category",
		"attributes": {
			"name": "Fiction"
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
		"detail": "CategoryId not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch the category"
	}]
}
```

## Save Category
- Endpoint : `/kindle-backend/api/categories`
- HTTP Method : `POST`
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `name` : String
- Response Body (Success) :
```
{
	"code": 201,
	"status": "Created",
	"data": [{
		"id": 1,
		"type": "category"
	}]
}
```
- Response Body (Fail) :
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot create the category"
	}]
}
```

## Update Category
- Endpoint : `/kindle-backend/api/categories/{categoryId}`
- HTTP Method : `PUT`
- Path Variable :
	- `categoryId` : Integer
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `name` : String
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "category"
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
		"detail": "CategoryId not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot update the category"
	}]
}
```

## Delete Category by CategoryId
- Endpoint : `/kindle-backend/api/categories/{categoryId}`
- HTTP Method : `DELETE`
- Path Variable :
	- `categoryId` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "category"
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
		"detail": "CategoryId not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot delete the category"
	}]
}
```
