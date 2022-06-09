package com.example.demo.accounts;

import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

//    Optional<Account> findByUser(User user);

      Optional<Account> findByIBANNo(String Iban);

}
