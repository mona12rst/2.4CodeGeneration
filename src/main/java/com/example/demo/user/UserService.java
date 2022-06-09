package com.example.demo.user;


import com.example.demo.accounts.Account;
import com.example.demo.enums.AccountStatusEnum;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.enums.UserStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;

    public String saveUser(User user) {
        user.setRoles(String.valueOf(UserRoleEnum.ROLE_CUSTOMER));
        user.setStatus(UserStatusEnum.UNDER_REVIEW);
        userRepository.save(user);
        return "Done";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersWithNoAccount() {
        return  userRepository.findAll().stream().filter(user -> user.getAccounts().size() == 0).collect(Collectors.toList());
    }

    public String updateUser(User user) {
        Optional<User> record = userRepository.findByUserName(user.getUserName());
        if (record.isPresent()) {
            record.get().setFirstName(user.getFirstName());
            record.get().setLastName(user.getLastName());
            record.get().setMobileNumber(user.getMobileNumber());
            record.get().setDateOfBirth(user.getDateOfBirth());
            record.get().setPassword(user.getPassword());
            record.get().setRoles(user.getRoles());
            userRepository.save(record.get());
            return "Done";
        } else
            return "NO USER WITH USERNAME: " + user.getUserName();
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    public String deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setStatus(UserStatusEnum.valueOf("DELETED"));
            for(Account a : user.get().getAccounts())
            {
                a.setAccountStatus(AccountStatusEnum.CLOSED);
            }
            userRepository.save(user.get());
            return "Done";
        }

        return "No User found";
    }


}
