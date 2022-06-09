package com.example.demo.accounts;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;


    @GetMapping("/accounts")
    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
    public List<Account> fetchAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
    public Optional<Account> getUserById(@PathVariable("id") int id) {
        return accountService.findAccountById(id);
    }


    @PostMapping("/accounts/{userid}")
    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
    public String createAccount(@PathVariable("userid") int userid, @RequestBody Account account) {
        return accountService.AddAccount(userid, account);
    }

    @PutMapping("/accounts/{id}")
    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
    public String updateAccount(@PathVariable int id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    // only deactivates, doesnt delete really
    @DeleteMapping("/accounts/{id}")
    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
    public String deleteAccount(@PathVariable("id") int id) {
        return accountService.deleteAccount(id);
    }


    @GetMapping("/findiban/{firstName}")
    public String findIbanByUserName(@PathVariable("firstName") String firstName) {
        return accountService.findIbanByUserName(firstName);
    }

    @GetMapping("balance/{IBAN}")
    public String balanceCheck( @RequestHeader("Authorization") String token,@PathVariable("IBAN") String IBAN) {
        return accountService.balanceCheck(token,IBAN);
    }


}
