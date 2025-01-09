# HTTPie

## Friends

### Get all
```
http http://localhost:8080/hola/v1/friends
```

### Get one
```
http http://localhost:8080/hola/v1/friends/1
```

### Create
```
http POST http://localhost:8080/hola/v1/friends < friend_create.json
```

### Update
```
http PUT http://localhost:8080/hola/v1/friends/7 < friend_update.json
```

### Delete one
```
http DELETE http://localhost:8080/hola/v1/friends/7
```
