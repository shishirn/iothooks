package com.shishir.iothooks.service;

import com.shishir.iothooks.exceptions.ResourceNotFoundException;
import com.shishir.iothooks.models.UserModel;
import com.shishir.iothooks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public UserModel getUserById(Long id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isPresent()) {
            return userModel.get();
        }
        throw new ResourceNotFoundException("User id:"+id);
    }

    public Iterable<UserModel> getAllUsers(String username) {
        if(username==null){
            return userRepository.findAll();
        }
        UserModel userModel = userRepository.findByUsername(username);
        if (userModel==null) {
            throw new ResourceNotFoundException("User :"+username+" not found");
        }

        HashSet<UserModel> hs = new HashSet<>();
        hs.add(userModel);
        return hs;

    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
