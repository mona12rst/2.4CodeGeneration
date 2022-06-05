package com.example.demo.transection;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public String saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        return "Done";
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public String updateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        return "Done";
    }

    public List<Transaction> findTransactionByIban(String iban) {
        return transactionRepository.findAllByFromIban(iban);
    }

    public String deleteTransaction(int id) {
        transactionRepository.deleteById(id);
        return "Done";
    }

    public Transaction findTransactionsBetween(LocalDate to, LocalDate from) {
        Optional<Transaction> transaction = transactionRepository.findByDateOfTransactionBetween(to, from);
        return transaction.orElse(null);
    }

    public List<Transaction> findFromIban(String iban) {
        return transactionRepository.findAllByFromIban(iban);
    }

    public List<Transaction> findToIban(String iban) {
        return transactionRepository.findAllByToIban(iban);
    }

    public List<Transaction> transferAmountEquals(long amount) {
        return transactionRepository.findAllByTransferAmountEquals(amount);
    }

    public List<Transaction> transferAmountLess(long amount) {
        return transactionRepository.findAllByTransferAmountLessThan(amount);
    }

    public List<Transaction> transferAmountLessAndEqual(long amount) {
        return transactionRepository.findAllByTransferAmountIsLessThanEqual(amount);
    }

    public List<Transaction> transferAmountGrater(long amount) {
        return transactionRepository.findAllByTransferAmountGreaterThan(amount);
    }

    public List<Transaction> transferAmountGraterAndEqual(long amount) {
        return transactionRepository.findAllByTransferAmountIsGreaterThanEqual(amount);
    }




}
