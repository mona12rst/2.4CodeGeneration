package com.example.demo.user;


import com.example.demo.enums.UserRoleEnum;
import com.example.demo.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "tbl_user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String userName;

    private String mobileNumber;

    private String firstName;

    private String lastName;

    private String DateOfBirth;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tbl_user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )

    private Collection<Role> roles=new ArrayList<>();


}
