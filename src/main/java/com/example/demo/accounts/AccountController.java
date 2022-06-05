package com.example.demo.accounts;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;


    @GetMapping("/accounts")
    public List<Account> fetchAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    public Optional<Account> getUserById(@PathVariable("id") int id) {
        return accountService.findAccountById(id);
    }


    @PostMapping("/account/{userid}")
    public String createAccount(@PathVariable("userid") int userid, @RequestBody Account account) {
        return accountService.AddAccount(userid, account);
    }

    @PutMapping("/account/{id}")
    public String updateUser(@PathVariable int id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }


    @DeleteMapping("/account/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        return accountService.deleteAccount(id);
    }


    @GetMapping("/findiban/{id}")
    public String findIbanById(@PathVariable("id") int id) {
        return accountService.findIbanByUserId(id);
    }


}
