# Transaction List API

## Get All Transaction List by TransactionId
- Endpoint : `/kindle-backend/api/transactionlists`
- HTTP Method : `GET`
- Request Parameter :
	- `transactionId` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "transactionlist",
		"relationships": {
			"merchant": {
				"data": [{
					"id": 1,
					"type": "merchant"
				}]
			},
			"book": {
				"data": [{
					"id": 1,
					"type": "book"
				}]
			}
		}
	}, {
		"id": 2,
		"type": "transactionlist",
		"relationships": {
			"merchant": {
				"data": [{
					"id": 2,
					"type": "merchant"
				}]
			},
			"book": {
				"data": [{
					"id": 2,
					"type": "book"
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
	}, {
		"id": 1,
		"type": "book",
		"attributes": {
			"title": "All The Light We Cannot See",
			"author": "Anthony Doerr",
			"year": 2009,
			"price": 50000,
			"document": "/uploads/book-example.svg"
	}, {
		"id": 2,
		"type": "book",
		"attributes": {
			"title": "All The Light We Cannot See",
			"author": "Anthony Doerr",
			"year": 2009,
			"price": 50000,
			"document": "/uploads/book-example.svg"
		}
	}]]
}
```
- Response Body (Fail) :
```
{
	"code": 400,
	"status": "Bad Request",
	"errors": [{
		"status": 404,
		"detail": "Transaction list not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch transaction list data"
	}]
}
```

## Save Transaction List
- Endpoint : `/kindle-backend/api/transactionlists`
- HTTP Method : `POST`
- Request Parameter :
	- `customerId` : Integer
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `bookSku` : int
	- `merchantId` : int
    	- `transactionId` : int
- Response Body (Success) :
```
{
	"code": 201,
	"status": "Created",
	"data": [{
		"id": 1,
		"type": "transactionlist"
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
		"detail": "Cannot create transaction list data"
	}]
}
```
