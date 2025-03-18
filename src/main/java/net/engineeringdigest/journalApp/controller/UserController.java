package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Controller is special type of component or class which handle our http request.
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.saveNewUser(userInDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null) {
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }


}










/*
    1. View(React, JSP, Thymeleaf) --->
    2. Presentation Layer(Controller) --->
    3. Business Logic Layer(Service) --->
    4. Repository Layer(Spring Data JPA/Spring Data MongoDB Repository's interfaces :- JpaRepository, CrudRepository for Data
       Access Abstraction or DAO(Data Access Object) pattern)--->
    5. Persistence Layer(interacts with the database, such as JPA/Hibernate/JDBC or any other ORM framework) --->
    6. Domain Model (Holds core business objects of an application, where Entity classes(for Represents persistent data stored
       in the database which requires a database connection) or POJOs (Plain Old Java Objects) used to hold data as a DTO(Data Transfer Object) or for business logic
       like Used for data transfer between layers (Controller → Service, etc.), so No Database Dependency) --->
    7. Static Resources (CSS, SCSS JS, Images).
*/




