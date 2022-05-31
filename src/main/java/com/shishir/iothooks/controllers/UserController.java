package com.shishir.iothooks.controllers;

import com.shishir.iothooks.models.User;
import com.shishir.iothooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public Iterable<User> getAllUsers(@RequestParam(name = "username", required = false) String username) {
        return userService.getAllUsers(username);
    }

    @DeleteMapping
    public void deleteAll(){
        userService.deleteAll();

    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id){
        userService.deleteById(id);

    }

}
