package com.example.demo;

import com.example.demo.accounts.Account;
import com.example.demo.accounts.AccountRepository;
import com.example.demo.enums.AccountStatusEnum;
import com.example.demo.enums.AccountTypeEnum;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.enums.UserStatusEnum;
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
import java.time.LocalDate;
import java.util.*;

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



        Account bankAccount = Account.builder().IBANNo("NL01INHO0000000001").accountType(AccountTypeEnum.CURRENT)
                .balance(0).dateOfOpening("").accountStatus(AccountStatusEnum.ACTIVE).build();

        accountRepository.save(bankAccount);

        User bankUser = User.builder().userName("Bank@g.com").mobileNumber("2266")
                .firstName("Bank").lastName("Mona").DateOfBirth("00-00-00").password("bankpass")
                .roles(String.valueOf(UserRoleEnum.ROLE_BANK)).accounts(Collections.singleton(bankAccount))
                .status(UserStatusEnum.ACTIVE).build();

        userRepository.save(bankUser);
    }



}
