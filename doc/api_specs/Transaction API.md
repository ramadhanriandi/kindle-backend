# Transaction API

## Get All Transaction by CustomerId
- Endpoint : `/kindle-backend/api/transactions`
- HTTP Method : `GET`
- Request Parameter :
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
		"type": "transaction",
		"attributes": {
			"date": "2019-12-27T06:51:25.079+0000",
			"total": 100000
		},
	}, {
		"id": 2,
		"type": "transaction",
		"attributes": {
			"date": "2019-12-27T06:51:25.079+0000",
			"total": 100000
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
		"detail": "Transaction not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch transaction data"
	}]
}
```

## Get Transaction by TransactionId
- Endpoint : `/kindle-backend/api/transactions/{transactionId}`
- HTTP Method : `GET`
- Path Variable :
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
		"type": "transaction",
		"attributes": {
			"date": "2019-12-27T06:51:25.079+0000",
			"total": 100000,
			"customerId": 1
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
		"detail": "Transaction not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch transaction data"
	}]
}
```

## Save Transaction
- Endpoint : `/kindle-backend/api/transactions`
- HTTP Method : `POST`
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `date` : Date
	- `total` : int
    	- `customerId` : int
- Response Body (Success) :
```
{
	"code": 201,
	"status": "Created",
	"data": [{
		"id": 1,
		"type": "transaction"
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
		"detail": "Cannot create transaction data"
	}]
}
```
