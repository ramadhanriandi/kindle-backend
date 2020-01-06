# Book API

## Get All Book
- Endpoint : `/kindle-backend/api/books`
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
		"type": "book",
		"attributes": {
			"title": "All The Light We Cannot See",
			"author": "Anthony Doerr",
			"year": 2009,
			"description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
			"price": 50000,
			"document": "/uploads/book-example.svg",
			"variant": "Black",
			"url": "/files/Book1.pdf",
			"categories": "Fiction;Drama;Romance"
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
		"id": 2,
		"type": "book",
		"attributes": {
			"title": "All The Light We Cannot See 2",
			"author": "Anthony Doerr 2",
			"year": 2010,
			"description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
			"price": 100000,
			"document": "/uploads/book-example.svg",
			"variant": "Black",
			"url": "/files/Book2.pdf",
			"categories": "Non-fiction;Horror"
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
		"detail": "Book not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch the book"
	}]
}
```

## Get Book by BookSku
- Endpoint : `/kindle-backend/api/books/{bookSku}`
- HTTP Method : `GET`
- Path Variable :
	- `bookSku` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "book",
		"attributes": {
			"title": "All The Light We Cannot See",
			"author": "Anthony Doerr",
			"year": 2009,
			"description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
			"price": 50000,
			"document": "/uploads/book-example.svg",
			"variant": "Black",
			"url": "/files/Book1.pdf",
			"categories": "Fiction;Drama;Romance"
    		},
		"relationships": {
			"merchant": {
				"data": [{
					"id": 1,
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
		"detail": "BookSku not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot fetch the book"
	}]
}
```

## Save Book
- Endpoint : `/kindle-backend/api/books`
- HTTP Method : `POST`
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `title` : String
    	- `author` : String
	- `year` : int
	- `description` : String
	- `price` : float
	- `merchantId` : int
	- `document` : String
	- `variant` : String
	- `url` : String
	- `categories` : String
- Response Body (Success) :
```
{
	"code": 201,
	"status": "Created",
	"data": [{
		"id": 1,
		"type": "book"
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
		"detail": "Cannot create the book"
	}]
}
```

## Update Book
- Endpoint : `/kindle-backend/api/books/{bookSku}`
- HTTP Method : `PUT`
- Path Variable :
	- `bookSku` : Integer
- Request Header :
	- Accept : `application/json`
- Request Body :
    	- `title` : String
    	- `author` : String
	- `year` : int
	- `description` : String
	- `price` : float
	- `merchantId` : int
	- `document` : String
	- `variant` : String
	- `url` : String
	- `categories` : String
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "book"
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
		"detail": "BookSku not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot update the book"
	}]
}
```

## Delete Book by BookSku
- Endpoint : `/kindle-backend/api/books/{bookSku}`
- HTTP Method : `DELETE`
- Path Variable :
	- `bookSku` : Integer
- Request Header :
	- Accept : `application/json`
- Response Body (Success) :
```
{
	"code": 200,
	"status": "OK",
	"data": [{
		"id": 1,
		"type": "book"
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
		"detail": "BookSku not found"
	}]
}
```
```
{
	"code": 500,
	"status": "Internal server error",
	"errors": [{
		"status": 500,
		"detail": "Cannot delete the book"
	}]
}
```
