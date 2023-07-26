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
