package com.example.demo.role;

import com.example.demo.accounts.Account;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RoleController {

    private RoleService roleService;

    @PostMapping("/role/{role}/{userid}")
    public String assignRole(@PathVariable("role") String role, @PathVariable("userid") int userid) {
//        return roleService.assignRole(userid, role);
        return "done";
    }
}
