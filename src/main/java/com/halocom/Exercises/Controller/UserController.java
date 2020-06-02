package com.halocom.Exercises.Controller;

import com.halocom.Exercises.Model.User;
import com.halocom.Exercises.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @RequestMapping(value = "/api/v1/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll(){
        List<User> users =userService.findAllUser();
        if (users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);

    }

    @RequestMapping(value = "/api/v1/user",  method = RequestMethod.POST)
    public ResponseEntity<User> createUser(
            @RequestBody User user,
            UriComponentsBuilder builder) {
        userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{id}")
                .buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/v1/user/{username}",  method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        User user = userService.findByUserName(username);
        if (user == null) {
            return new ResponseEntity<>(user,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
