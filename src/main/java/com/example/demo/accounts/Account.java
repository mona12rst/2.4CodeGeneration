package com.example.demo.accounts;


import com.example.demo.enums.AccountStatusEnum;
import com.example.demo.enums.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    private int userId;

    private long transactionLimit = 5000;

    private long dayLimit = 2;

    private long absoluteLimit = 100;


}
