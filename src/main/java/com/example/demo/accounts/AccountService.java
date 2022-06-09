package com.example.demo.accounts;

import com.example.demo.enums.AccountStatusEnum;
import com.example.demo.enums.AccountTypeEnum;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.enums.UserStatusEnum;
import com.example.demo.role.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.utils.IBANGenerator;
import com.example.demo.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public String AddAccount(int userId, Account account) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return "No user Present";
        }else {
            AccountTypeEnum account_Type = account.getAccountType();
            AccountStatusEnum account_Status = account.getAccountStatus();
            if (account_Type == null) {
                return "Invalid Account Type";
            }
            if (account_Status == null) {
                return "Invalid Account Status";
            } else {
                account.setIBANNo(IBANGenerator.generateIBAN());
                account.setAccountType(account_Type);
                account.setAccountStatus(account_Status);
                accountRepository.save(account);

                user.get().setStatus(UserStatusEnum.ACTIVE);
                user.get().getAccounts().add(account);
                userRepository.save(user.get());
                return "done";
            }
        }

    }

    public String updateAccount(int id, Account account) {
        Optional<Account> a = accountRepository.findById(id);
        if (a.isPresent()) {
            a.get().setAccountStatus(account.getAccountStatus());
            a.get().setBalance(account.getBalance());
            a.get().setAccountType(account.getAccountType());
            a.get().setAbsoluteLimit(account.getAbsoluteLimit());
            a.get().setTransactionLimit(account.getTransactionLimit());
            a.get().setDayLimit(account.getDayLimit());
            accountRepository.save(a.get());
            return "Done";
        } else {
            return "No Account Found Against id " + id;
        }

    }

    public Optional<Account> findAccountById(int id) {
        return accountRepository.findById(id);
    }

    public String deleteAccount(int id) {
        Optional<Account> a = accountRepository.findById(id);
        if (a.isPresent()) {
            a.get().setAccountStatus(AccountStatusEnum.CLOSED);
            accountRepository.save(a.get());
            return "Done";
        } else {
            return "No Account Found Against id " + id;
        }

    }

    public String balanceCheck(String token,String IBAN){

        Optional<User> user = userRepository.findByUserName(jwtUtil.extractUsername(token.substring(7)));
        if(user.isPresent()){
            for (Account account : user.get().getAccounts()){
                if(account.getIBANNo().equals(IBAN)){
                    return "Your Account Balance is: " + account.getBalance();
                }
            }
        }
       return "No user Found check your token";
    }

    public String findIbanByUserName(String name) {

        StringBuilder IBAN = new StringBuilder();
        Optional<User> user = userRepository.findByFirstName(name);
        if(user.isEmpty()){
            return "No User Found";
        }
       for( Account accounts : user.get().getAccounts()){
           IBAN.append(" IBAN: ").append(accounts.getIBANNo());
       }
       return IBAN.toString();
    }


}
