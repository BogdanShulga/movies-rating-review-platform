# _*Tips to test Movies Rating Review Platform*_

***
>add some data to the database:  
GET: localhost:8080/migrate
***
>add a movie:  
POST: localhost:8080/admin/movie  
BODY:
```json
{  
    "name": "Spider-Man: Far from Home",  
    "category": "action",  
    "director": "Jon Watts",  
    "shortDescription": "In this adventure, Spider-man faces new challenges after Avengers: Endgame.",  
    "rate": {  
        "rateValue": 9.9,  
        "voteCount": 4569  
    }  
}  
```
***
>remove a movie by id:  
DELETE: localhost:8080/admin/movie/{id}
***
>update an info about a movie:  
PUT: localhost:8080/admin/movie  
BODY:
```json
{  
    "id": 2,  
    "name": "John Wick: Chapter 3 - Parabellum",  
    "category": "thriller",  
    "director": "Chad Stahelski",  
    "shortDescription": "added something new",  
    "rate": {  
        "rateValue": 8.6,  
        "voteCount": 8465  
    }  
}  
```
***
>get an info about a particular movie including reviews:  
GET: localhost:8080/movie/reviews/{id}
***
>add a rate to a movie:  
PATCH: localhost:8080/movie/rate  
BODY:
```json
{    
    "movieDtoId" : 1,    
    "rate" : 7.5    
}   
```
***
>get a list of all movies. (Name, category, rating. Without reviews):  
GET: localhost:8080/movie/all
***
>get a list of all movies sorted by rating (from highest to lowest):  
GET: localhost:8080/stats/rating
***
>get a list of all movies sorted by category:  
GET: localhost:8080/stats/category
***
>get a movies list with a rate grater then a rateValue:  
GET: localhost:8080/movie/{rateValue}
***
>add a review to a movie  
POST: localhost:8080/review/movie    
BODY:
```json
{
    "movieId" : 1,
    "reviewMessage" : "supper dupper movie",
    "isLiked" : "true"
}
```



