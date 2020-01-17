# Merchant API

## Get All Merchant
- Endpoint : `/kindle-backend/api/merchants`
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
		"type": "merchant",
		"attributes": {
			"fullname": "Gramedia",
			"status": "Active"
    		},
	}, {
		"id": 1,
		"type": "merchant",
		"attributes": {
			"fullname": "Gramedia 2",
			"status": "Inactive"
    		},
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
		"detail": "Merchant not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch merchant data"
	}]
}
```

## Get Merchant by MerchantId
- Endpoint : `/kindle-backend/api/merchants/{merchantId}`
- HTTP Method : `GET`
- Path Variable :
	- `merchantId` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "merchant",
		"attributes": {
			"username": "gramedia",
			"email": "gramedia@example.com",
			"password": "helloworld",
			"fullname": "Gramedia",
			"description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
			"phone": "1234567890",
			"status": "Active"
    		},
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
		"detail": "Merchant not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch merchant data"
	}]
}
```

## Save Merchant
- Endpoint : `/kindle-backend/api/merchants`
- HTTP Method : `POST`
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `username` : String
	- `fullname` : String
    	- `email` : String
	- `password` : String
	- `phone` : int
	- `description` : String
	- `status` : String
- Response Body (Success) :
```
{
	"code": 201,
	"status": "Created",
	"data": [{
		"id": 1,
		"type": "merchant"
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
		"detail": "Cannot create merchant data"
	}]
}
```

## Update Merchant
- Endpoint : `/kindle-backend/api/merchants/{merchantId}`
- HTTP Method : `PUT`
- Path Variable :
	- `merchantId` : Integer
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `username` : String
	- `fullname` : String
    	- `email` : String
	- `password` : String
	- `phone` : int
	- `description` : String
	- `status` : String
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "merchant"
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
		"detail": "Merchant not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot update merchant data"
	}]
}
```

## Delete Merchant by MerchantId
- Endpoint : `/kindle-backend/api/merchants/{merchantId}`
- HTTP Method : `DELETE`
- Path Variable :
	- `merchantId` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "merchant"
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
		"detail": "Merchant not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot delete merchant data"
	}]
}
```

## Login Merchant
- Endpoint : `/kindle-backend/api/merchants/login`
- HTTP Method : `POST`
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `email` : String
	- `password` : int
- Response Body (Success) :
```
{
	"code": 201,
	"status": "Created",
	"data": [{
		"id": 1,
		"type": "merchant"
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
		"detail": "Cannot fetch merchant data"
	}]
}
```

## Get Merchant Catalog
- Endpoint : `/kindle-backend/api/merchants/{merchantId}/catalog`
- HTTP Method : `GET`
- Path Variable :
	- `merchantId` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"sku": 1,
		"type": "book",
		"attributes": {
			"document": "/uploads/book-example.svg"
    		}
	}, {
		"sku": 2,
		"type": "book",
		"attributes": {
			"document": "/uploads/book-example.svg"
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
		"detail": "Merchant not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch merchant catalog data"
	}]
}
```
