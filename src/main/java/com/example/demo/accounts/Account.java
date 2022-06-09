package com.example.demo.accounts;


import com.example.demo.enums.AccountStatusEnum;
import com.example.demo.enums.AccountTypeEnum;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String IBANNo;

    private AccountTypeEnum accountType;

    private long balance;

    private String dateOfOpening;

    private AccountStatusEnum accountStatus;

    private long transactionLimit = 20; // the limit for number of transactions : fixme:

    private long dayLimit = 2000; // One Day Transaction Limit // the user should be able to update this one fixme:

    private long absoluteLimit = 100; // Minimum Amount that should be in account


}
