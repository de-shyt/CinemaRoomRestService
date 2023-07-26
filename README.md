# Cinema Room REST Service (Java)

A simple Spring REST service that helps managing a small movie theater.



## Technical description

Features used in the project: 

- Spring Boot 
- Jackson 



Project structure:

```txt
└── cinema
    ├── buisnesslayer
    │   ├── CinemaRoom
    │   │   ├── CinemaRoom.java                # Respresents a cinema room
    │   │   └── CinemaRoomManager.java         # Responsible for `CinemaRoom` objects
    │   ├── CinemaRoomConfig.java              # Configurations for `CinemaRoom` objects
    │   ├── CinemaRoomController.java          # Main application logic
    │   ├── exceptions                         # Custom exceptions 
    │   │   ├── CustomExceptionHandler.java
    │   │   ├── ErrorResponse.java             # Response body in case there is an error
    │   │   ├── TheSeatIsTakenException.java
    │   │   ├── WrongPasswordException.java
    │   │   ├── WrongSeatIndexException.java
    │   │   └── WrongTokenException.java
    │   └── json                               # Responsible for generating JSON objects:
    │       ├── CinemaRoomJson.java            #   Info about cinema room
    │       ├── CinemaRoomStatisticsJson.java  #   Sales statistics 
    │       ├── PurchasedTicketJson.java       #   Info about purchased ticket 
    │       ├── ReturnedTicketJson.java        #   Info about returned ticket 
    │       ├── SeatJson.java                  #   Info about a seat (row, column)
    │       └── TokenJson.java                 #   Info about customer's token
    ├── CinemaApplication.java                 # Launches application using Spring Boot
    └── databaselayer
        └── PurchasedTicketsDatabase.java      # Stores <token, tiket info> pairs 

```





## Project Features

There are several endpoints used in the application, all of them return the response in JSON format.



### `/seats`

Returns the information about the movie theater: the total amount of rows and columns and the list of available seats.

**Example:**

```json
// GET /seats

// Response:
{
   "total_rows":5,
   "total_columns":6,
   "available_seats":[
      {
         "row":1,
         "column":1
      },
      {
         "row":3,
         "column":2
      },
      {
         "row":5,
         "column":6
      }
   ]
}
```



### `/purchase`

Marks a booked ticket as purchased. It receives row and column in JSON format and either continues the booking process or throws `TheSeatIsTakenException`. If there is an exception, the `400 (Bad Request)` status code is returned.

When a customer buys a ticket, they the response body contains a randomly generated `UUID` token. The token and the data about the customer's ticket are stored in `PurchasedTicketsDatabase`.

**Examples:**

```json
// POST /purchase
{
    "row": 1,
    "column": 1
}

// Response:
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```

```json
// POST /purchase
{
    "row": 1,
    "column": 1
}

// Response:
{
    "error": "The ticket has been already purchased!"
}
```



### `/return`

Allows customers to refund their tickets. It receives a token in JSON format and either continues the return process or throws `WrongTokenException`. If there is an exception, the `400 (Bad Request)` status code is returned.

**Examples:**

```json
// POST /return
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee"
}

// Response:
{
    "returned_ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```

```json
// POST /return
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee"
}

// Response:
{
    "error": "Wrong token!"
}
```







### `/stats`

Shows sales statistics: current income, amount of purchased and available seats. 

Showing statistics is possible only for the theater managers. To confirm the role, the user passes the password as a URL parameter. If the password is correct, the statistics is shown, otherwise, `WrongPasswordException` is thrown and `401 (Unauthorized)` status code is returned. 



**Examples:**

```json
// GET /stats
{
    "error": "The password is wrong!"
}
```

```json
// GET /stats&password=<user_pwd>
{
    "error": "The password is wrong!"
}
```

```json
// GET /stats&password=<manager_pwd>

// Response:
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```

