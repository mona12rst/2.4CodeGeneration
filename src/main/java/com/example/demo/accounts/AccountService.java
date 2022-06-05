package com.example.demo.accounts;

import com.example.demo.enums.AccountStatusEnum;
import com.example.demo.enums.AccountTypeEnum;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.role.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.utils.IBANGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private UserRepository userRepository;



    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public String AddAccount(int userId, Account account) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            account.setIBANNo(IBANGenerator.generateIBAN());
            account.setAccountStatus(AccountStatusEnum.ACTIVE);
            account.setUserId(userId);
            accountRepository.save(account);
            return "done";
        } else {
            return "No user Present";
        }
    }

    public String updateAccount(int id , Account account) {
        Optional<Account> a = accountRepository.findById(id);
        if(a.isPresent()) {
            a.get().setAccountStatus(account.getAccountStatus());
            a.get().setBalance(account.getBalance());
            a.get().setAccountType(account.getAccountType());
            accountRepository.save(a.get());
        }
        return "Done";
    }

    public Optional<Account> findAccountById(int id) {
        return accountRepository.findById(id);
    }

    public String deleteAccount(int id) {
        Optional<Account> a = accountRepository.findById(id);
        if(a.isPresent()) {
            a.get().setAccountStatus(AccountStatusEnum.CLOSED);
            accountRepository.save(a.get());
        }
        return "Done";
    }

    public String findIbanByUserId(int id) {
        Optional<Account> data = accountRepository.findByUserId(id);
        if (data.isPresent()) {
            return data.get().getIBANNo();
        } else {
            return "No Record Found";
        }
    }


}
