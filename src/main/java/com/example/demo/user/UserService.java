package com.example.demo.user;


import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;

    public String saveUser(User user) {
        userRepository.save(user);
        return "Done";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String updateUser(User user) {
           userRepository.save(user);
           return "Done";
    }

    public Optional<User> findUserById(int id){
        return userRepository.findById(id);
    }

    public String deleUser(int id){
         userRepository.deleteById(id);
         return "Done";
    }




}
