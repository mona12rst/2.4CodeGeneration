package com.example.demo.transection;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/transaction")
    public String addTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAlltransaction() {
        return transactionService.getAllTransaction();
    }

    @PutMapping("/transaction")
    public String updatetransaction(@RequestBody Transaction transaction) {
        return transactionService.updateTransaction(transaction);
    }

    @GetMapping("/transaction/{iban}")
    public List<Transaction> gettransactionById(@PathVariable("iban") String iban) {
        return transactionService.findTransactionByIban(iban);
    }

    @DeleteMapping("/transaction/{id}")
    public String deletetransaction(@PathVariable("id") int id) {
        return transactionService.deleteTransaction(id);
    }


    @GetMapping("/transaction/betweenDates/{to}/{from}")
    public Transaction findTransactionsBetween(@PathVariable("to") LocalDate to, @PathVariable("from") LocalDate from) {
        return transactionService.findTransactionsBetween(to, from);
    }

    @GetMapping("/transaction/FromIban/{iban}")
    public List<Transaction> findFromIban(@PathVariable("iban") String iban) {
        return transactionService.findFromIban(iban);
    }

    @GetMapping("/transaction/ToIban/{iban}")
    public List<Transaction> findToIban(@PathVariable("iban") String iban) {
        return transactionService.findToIban(iban);
    }

    @GetMapping("/transaction/amountEqual/{amount}")
    public List<Transaction> transferAmountEquals(@PathVariable("amount") long amount) {
        return transactionService.transferAmountEquals(amount);
    }

    @GetMapping("/transaction/amountLess/{amount}")
    public List<Transaction> transferAmountLess(@PathVariable("amount") long amount) {
        return transactionService.transferAmountLess(amount);
    }

    @GetMapping("/transaction/amountLessEqual/{amount}")
    public List<Transaction> transferAmountLessAndEqual(@PathVariable("amount") long amount) {
        return transactionService.transferAmountLessAndEqual(amount);
    }

    @GetMapping("/transaction/amountGrater/{amount}")
    public List<Transaction> transferAmountGrater(@PathVariable("amount") long amount) {
        return transactionService.transferAmountGrater(amount);
    }

    @GetMapping("/transaction/amountGraterEqual/{amount}")
    public List<Transaction> transferAmountGraterAndEqual(@PathVariable("amount") long amount) {
        return transactionService.transferAmountGraterAndEqual(amount);
    }


}
