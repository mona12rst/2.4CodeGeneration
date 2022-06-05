package com.example.demo;

import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void CheckingRepoTest() throws Exception {
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void SaveRoleTest() {

        roleRepository.save(new Role(0,"Bank"));

//        Role adminRole = roleRepository.findByName("Bank").orElse(null);

//        User user = new User(0, "Bank@g.com", "2265", "Bank", "Mona",
//                "00-00-00", "bankpass", Arrays.asList(adminRole));
//        userRepository.save(user);
//
//        Optional<User> user1 = userRepository.findById(0);
//        assertEquals("Bank", user1.get().getFirstName());
    }

//    @Test
//    @AfterTestMethod("SaveRoleTest")
//    public void ShowRoleTest() {
//        Role adminRole = roleRepository.findById(0).orElse(null);
//        assertEquals("Bank", adminRole.getName());
//    }

}
