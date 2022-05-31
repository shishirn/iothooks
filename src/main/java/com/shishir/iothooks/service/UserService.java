package com.shishir.iothooks.service;

import com.shishir.iothooks.exceptions.ResourceNotFoundException;
import com.shishir.iothooks.models.User;
import com.shishir.iothooks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> userModel = userRepository.findById(id);
        if (userModel.isPresent()) {
            return userModel.get();
        }
        throw new ResourceNotFoundException("User id:"+id);
    }

    public Iterable<User> getAllUsers(String username) {
        if(username==null){
            return userRepository.findAll();
        }
        User user = userRepository.findByUsername(username);
        if (user ==null) {
            throw new ResourceNotFoundException("User :"+username+" not found");
        }

        HashSet<User> hs = new HashSet<>();
        hs.add(user);
        return hs;

    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
