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

## Get Customer Cart
- Endpoint : `/kindle-backend/api/customers/{customerId}/cart`
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
		"sku": 1,
		"type": "book",
		"attributes": {
			"title": "All The Light We Cannot See",
			"author": "Anthony Doerr",
			"year": 2009,
			"price": 50000,
			"document": "/uploads/book-example.svg"
    		},
		"relationships": {
			"merchant": {
				"data": [{
					"id": 1,
					"type": "merchant"
				}]
			}
		}
	}, {
		"sku": 2,
		"type": "book",
		"attributes": {
			"title": "All The Light We Cannot See 2",
			"author": "Anthony Doerr 2",
			"year": 2010,
			"price": 100000,
			"document": "/uploads/book-example.svg"
    		},
		"relationships": {
			"merchant": {
				"data": [{
					"id": 2,
					"type": "merchant"
				}]
			}
		}
	}],
	"included": [{
		"id": 1,
		"type": "merchant",
		"attributes": {
			"fullname": "Gramedia"
	}, {
		"id": 2,
		"type": "merchant",
		"attributes": {
			"fullname": "Gramedia 2"
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
		"detail": "Cannot fetch customer cart data"
	}]
}
```

## Get Customer Library
- Endpoint : `/kindle-backend/api/customers/{customerId}/library`
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
		"detail": "Cannot fetch customer library data"
	}]
}
```

## Get Customer Wishlist
- Endpoint : `/kindle-backend/api/customers/{customerId}/wishlist`
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
		"sku": 1,
		"type": "book",
		"attributes": {
			"title": "All The Light We Cannot See",
			"author": "Anthony Doerr",
			"year": 2009,
			"price": 50000,
			"document": "/uploads/book-example.svg"
    		},
		"relationships": {
			"merchant": {
				"data": [{
					"id": 1,
					"type": "merchant"
				}]
			}
		}
	}, {
		"sku": 2,
		"type": "book",
		"attributes": {
			"title": "All The Light We Cannot See 2",
			"author": "Anthony Doerr 2",
			"year": 2010,
			"price": 100000,
			"document": "/uploads/book-example.svg"
    		},
		"relationships": {
			"merchant": {
				"data": [{
					"id": 2,
					"type": "merchant"
				}]
			}
		}
	}],
	"included": [{
		"id": 1,
		"type": "merchant",
		"attributes": {
			"fullname": "Gramedia"
	}, {
		"id": 2,
		"type": "merchant",
		"attributes": {
			"fullname": "Gramedia 2"
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
		"detail": "Cannot fetch customer wishlist data"
	}]
}
```
