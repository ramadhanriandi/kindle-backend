# Customer API

## Get All Customer
- Endpoint : `/kindle-backend/api/customers`
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
		"type": "customer",
		"attributes": {
			"username": "riandi123",
			"status": "Active",
    		},
	}, {
		"id": 2,
		"type": "customer",
		"attributes": {
			"username": "riandi456",
			"status": "Inactive",
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
		"detail": "Customer not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch customer data"
	}]
}
```

## Get Customer by CustomerId
- Endpoint : `/kindle-backend/api/customers/{customerId}`
- HTTP Method : `GET`
- Path Variable :
	- `customerId` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "customer",
		"attributes": {
			"username": "riandi123",
			"status": "Active",
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
		"detail": "CustomerId not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch customer data"
	}]
}
```

## Save Customer
- Endpoint : `/kindle-backend/api/customers`
- HTTP Method : `POST`
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `username` : String
    	- `email` : String
	- `password` : int
	- `status` : String, default is `"Active"`
- Response Body (Success) :
```
{
	"code": 201,
	"status": "Created",
	"data": [{
		"id": 1,
		"type": "customer"
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
		"detail": "Cannot create customer data"
	}]
}
```

## Update Customer
- Endpoint : `/kindle-backend/api/customers/{customerId}`
- HTTP Method : `PUT`
- Path Variable :
	- `customerId` : Integer
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `username` : String
    	- `email` : String
	- `password` : int
	- `status` : String, default is `"Active"`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "customer"
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
		"detail": "CustomerId not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot update customer data"
	}]
}
```

## Delete Customer by CustomerId
- Endpoint : `/kindle-backend/api/customers/{customerId}`
- HTTP Method : `DELETE`
- Path Variable :
	- `customerId` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "customer"
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
		"detail": "CustomerId not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot delete customer data"
	}]
}
```

## Login Customer
- Endpoint : `/kindle-backend/api/customers/login`
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
		"type": "customer"
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
		"detail": "Cannot fetch customer data"
	}]
}
```
