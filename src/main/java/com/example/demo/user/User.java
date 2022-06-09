package com.example.demo.user;


import com.example.demo.accounts.Account;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.enums.UserStatusEnum;
import com.example.demo.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "tbl_user")
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "UserName is Required")
    @Column(unique = true)
    private String userName;

    @NotEmpty(message = "Mobile Number is required")
    private String mobileNumber;

    @NotEmpty(message = "FirstName is required")
    private String firstName;

    @NotEmpty(message = "LastName is required")
    private String lastName;

    @NotEmpty(message = "Date of birth is required")
    private String DateOfBirth;

    @NotEmpty(message = "Password is required")
    private String password;

    private String roles;

    private UserStatusEnum status;

    @OneToMany
    private Set<Account> accounts = new HashSet<>();
}
