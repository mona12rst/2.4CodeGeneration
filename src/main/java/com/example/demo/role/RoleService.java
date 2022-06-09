package com.example.demo.role;

import com.example.demo.enums.UserRoleEnum;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void  addRole(){

        roleRepository.save(new Role(1,"Client"));
        roleRepository.save(new Role(2,"Employee"));
        roleRepository.save(new Role(3,"Bank"));
    }

//    public String assignRole(int userId, String role) {
//        User user = userService.findUserById(userId).orElse(null);
//        Role newRoles = roleRepository.findByName(role).orElse(null);
//        if (user != null && newRoles != null) {
//            Collection<Role> oldRole = user.getRoles();
//            oldRole.add(newRoles);
//            user.setRoles(oldRole);
//            userService.saveUser(user);
//            return "Done";
//        }
//        return "Check your provided Details twice";
//    }

//    public String removeRole(int userId, UserRoleEnum role) {
//
//        User user = userRepository.findById(userId).orElse(null);
//            Role newRoles = roleRepository.findByRole(role).orElse(null);
//            if (newRoles != null && user != null) {
//
//                user.setUserRoles(roles);
//                userRepository.save(user);
//                return "Done";
//            }
//            return "Check your provided Details twice";
//
//    }

}
