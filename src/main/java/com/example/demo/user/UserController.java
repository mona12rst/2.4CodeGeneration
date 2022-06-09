package com.example.demo.user;


import com.example.demo.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/noaccount")
    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
    public List<User> getAllUserWithNoAccount() {
        return userService.getAllUsersWithNoAccount();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }

    @GetMapping("/profile")
    public Optional<User> getProfile(@RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.substring(7));
        return userService.findUserByUserName(username);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
    public String deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

}
