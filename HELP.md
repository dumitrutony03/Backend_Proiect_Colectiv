1.GET http://localhost:8080/user/Cristi
    - returns user by userName
2.GET http://localhost:8080/user/all
    - return all users 
3.POST http://localhost:8080/user/login
    - clients sends a json BODY. Check src/main/java/proiectcolectiv/dto/UserDto.java for variable names.
    - returns the sent json.
    - send 400 Bad Request if userName or password is not wrong.
4.POST http://localhost:8080/user/
    - register. Adds a user to DB.
    - returns added User.