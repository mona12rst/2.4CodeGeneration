package com.example.demo.transection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> findAllByFromIban(String value);

    List<Transaction> findByFromIbanAndDateOfTransactionBetween(String Iban, LocalDate to, LocalDate from);

    List<Transaction> findAllByToIban(String integer);

    List<Transaction> findAllByTransferAmountEquals(long amount);

    List<Transaction> findAllByTransferAmountLessThan(long integer);

    List<Transaction> findAllByTransferAmountIsLessThanEqual(long integer);

    List<Transaction> findAllByTransferAmountGreaterThan(long integer);

    List<Transaction> findAllByTransferAmountIsGreaterThanEqual(long integer);

    List<Transaction> findAllByDateOfTransaction(LocalDate integer);
}
