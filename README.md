Home Assignment
---------------------------------------------------------------------------------------------------------------------------
Application Specification : Created a RESTful application using Java and Spring Boot. Run application on localhost and use below link.
---------------------------------------------------------------------------------------------------------------------------
Swagger link - http://localhost:8080/swagger-ui.html
---------------------------------------------------------------------------------------------------------------------------
Database - http://localhost:8080/h2
---------------------------------------------------------------------------------------------------------------------------

All APIs:-

1. GET all Product API

GET - http://localhost:8080/getAllProduct

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

--------------------------------------------------------------------------------

2.  Create Product API

POST - http://localhost:8080/createProduct

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

--------------------------------------------------------------------------------

3. Update Product API

PUT - http://localhost:8080/updateProduct/1

Request - application/json

{
        "price": 1922,
        "name": "Indian"
}

Repsonse

{
    "productId": 1,
    "price": 1922,
    "name": "Indian",
    "sku": "ab4d9fd2-52c7-4bd1-b12b-f41b3f15740d",
    "createdDate": "2020-03-22"
}


4. Product Soft Delete

Delete - http://localhost:8080/deleteProduct/2

Repsonse

{
    "message": "Product is deleted."
}

--------------------------------------------------------------------------------

5. Create Order

Post - http://localhost:8080/createOrder

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

--------------------------------------------------------------------------------

6. Get Order based on date

GET - http://localhost:8080/getOrder?fromDate=2020-03-22&toDate=2020-03-22

Response

{
    "data": [
        {
            "totalPrice": 30002,
            "order_1": {
                "id": 1,
                "email": "vikki@gmail.com",
                "createdDate": "2020-03-22",
                "ordersDetails": [
                    {
                        "id": 1,
                        "quantity": 3,
                        "price": 10000.00,
                        "productId": 4
                    },
                    {
                        "id": 2,
                        "quantity": 2,
                        "price": 1.00,
                        "productId": 3
                    }
                ]
            }
        },
        {
            "totalPrice": 2003,
            "order_2": {
                "id": 2,
                "email": "vikki@gmail.com",
                "createdDate": "2020-03-22",
                "ordersDetails": [
                    {
                        "id": 3,
                        "quantity": 3,
                        "price": 1.00,
                        "productId": 2
                    },
                    {
                        "id": 4,
                        "quantity": 2,
                        "price": 1000.00,
                        "productId": 1
                    }
                ]
            }
        }
    ]
}
