Home Assignment

Application Specification : Created a RESTful application using Java and Spring Boot.

Swagger link - http://localhost:8080/swagger-ui.html

Database - http://localhost:8080/h2


1. GET API
http://localhost:8080/getAllProduct

Response
[
    {
        "productId": 1,
        "price": 102.00,
        "name": "Ball",
        "sku": "aa631429-e400-4c2d-a4d8-f165e99d235b",
        "createdDate": "2020-03-22"
    }
]

2. POST API
http://localhost:8080/createProduct

Request - application/json
{
        "price": 102,
        "name": "Ball"
}

Repsonse

{
    "productId": 1,
    "price": 102,
    "name": "Ball",
    "sku": "aa631429-e400-4c2d-a4d8-f165e99d235b",
    "createdDate": "2020-03-22T12:07:23.063+0000"
}

3. PUT API
http://localhost:8080/updateProduct/1

Request - application/json

{
        "price": 1922,
        "name": "Indian"
}

repsonse
{
    "productId": 1,
    "price": 1922,
    "name": "Indian",
    "sku": "ab4d9fd2-52c7-4bd1-b12b-f41b3f15740d",
    "createdDate": "2020-03-22"
}


4.Delete API
http://localhost:8080/deleteProduct/2

Repsonse
{
    "message": "Product is deleted."
}


5. Post API
http://localhost:8080/createOrder

Request
{
	"email":"rakesh@gmail.com",
	"productOrder":[{ 
		"quantity":2,
		"id":1
	}]
}

Response
{
    "id": 2,
    "email": "rakesh@gmail.com",
    "createdDate": "2020-03-22T11:51:40.477+0000",
    "ordersDetails": [
        {
            "id": 2,
            "quantity": 2,
            "price": 1922.00,
            "productId": 1
        }
    ]
}


6. GET API

http://localhost:8080/getOrder?fromDate=2020-03-22&toDate=2020-03-22

Response
{
    "data": [
        {
            "id": 1,
            "email": "rakesh@gmail.com",
            "createdDate": "2020-03-22",
            "ordersDetails": [
                {
                    "id": 1,
                    "quantity": 2,
                    "price": 1922.00,
                    "productId": 1
                }
            ]
        },
        {
            "id": 2,
            "email": "rakesh@gmail.com",
            "createdDate": "2020-03-22",
            "ordersDetails": [
                {
                    "id": 2,
                    "quantity": 2,
                    "price": 1922.00,
                    "productId": 1
                }
            ]
        }
    ],
    "totalPrice": 3848
}
