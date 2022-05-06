package com.shishir.iothooks.controllers;

import com.shishir.iothooks.models.UserModel;
import com.shishir.iothooks.repositories.UserRepository;
import com.shishir.iothooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel addUser(@RequestBody UserModel user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public Iterable<UserModel> getAllUsers(@RequestParam(name = "username", required = false) String username) {
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
