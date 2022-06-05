package com.example.demo;

import com.example.demo.accounts.Account;
import com.example.demo.accounts.AccountRepository;
import com.example.demo.enums.AccountStatusEnum;
import com.example.demo.enums.AccountTypeEnum;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class DemoApplication {



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @PostConstruct
    public void init() {


        roleRepository.save(new Role(0,"Bank"));

        Role adminRole = roleRepository.findByName("Bank").orElse(null);

        userRepository.save(new User(0, "Bank@g.com", "2265", "Bank", "Mona",
                "00-00-00", "bankpass", Arrays.asList(adminRole)));

        accountRepository.save(new Account(0, "NL01INHO0000000001", AccountTypeEnum.CURRENT, 0,
                "", AccountStatusEnum.ACTIVE, 0, 100000000, 10000000, 0));
    }


}
