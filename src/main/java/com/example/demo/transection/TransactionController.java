package com.example.demo.transection;

import com.example.demo.utils.JwtUtil;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions/")
public class TransactionController {

    private final TransactionService transactionService;
    private final JwtUtil jwtUtil;

    @PostMapping
    //Employee should be able to do transaction from any account
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE')")
    public String makeTransaction(@RequestBody TransactionDTO transaction, @RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.substring(7));
        return transactionService.makeTransaction(transaction, username);
    }

//    @GetMapping("/transactions")
//    @PreAuthorize("hasAnyRole('ROLE_BANK','ROLE_EMPLOYEE')")
//    public List<Transaction> getAlltransaction() {
//        return transactionService.getAllTransaction();
//    }

//    @PutMapping("/transaction")
//    @PreAuthorize("hasAnyRole('ROLE_SPECIAL')")
//    public String updatetransaction(@RequestBody Transaction transaction) {
//        return transactionService.updateTransaction(transaction);
//    }

    @GetMapping("{IBAN}")
    public List<Transaction> gettransactionByIBAN(@PathVariable("IBAN") String IBAN, @RequestHeader("Authorization") String token) {
        return transactionService.findTransactionByIban(IBAN, token);
    }

//    @DeleteMapping("{id}")
//    public String deletetransaction(@PathVariable("id") int id) {
//        return transactionService.deleteTransaction(id);
//    }


    @GetMapping("{IBAN}/betweenDates/{to}/{from}")
    public List<Transaction> findByFromIBANAndDateOfTransactionBetween(@PathVariable("IBAN") String IBAN, @PathVariable("to") String to, @PathVariable("from") String from, @RequestHeader("Authorization") String token) {
        return transactionService.findByFromIbanAndDateOfTransactionBetween(IBAN, to, from, token);
    }

//    @GetMapping("{IBAN}/fromIBAN")
//    public List<Transaction> findFromIBAN(@PathVariable("IBAN") String IBAN,@RequestHeader("Authorization") String token) {
//        return transactionService.findFromIban(IBAN,token);
//    }

    @GetMapping("{IBAN}/toIBAN/{receiverIBAN}")
    public List<Transaction> findToIBAN(@PathVariable("IBAN") String IBAN, @RequestHeader("Authorization") String token, @RequestParam("receiverIBAN") String reciverIban) {
        return transactionService.findToIban(IBAN, token, reciverIban);
    }

    @GetMapping("{IBAN}/amountEqual/{amount}")
    public List<Transaction> transferAmountEquals(@PathVariable("IBAN") String IBAN, @PathVariable("amount") long amount, @RequestHeader("Authorization") String token) {
        return transactionService.transferAmountEquals(IBAN, amount, token);
    }

    @GetMapping("{IBAN}/amountLess/{amount}")
    public List<Transaction> transferAmountLess(@PathVariable("IBAN") String IBAN, @PathVariable("amount") long amount, @RequestHeader("Authorization") String token) {
        return transactionService.transferAmountLess(IBAN, amount, token);
    }

    @GetMapping("{IBAN}/amountLessEqual/{amount}")
    public List<Transaction> transferAmountLessAndEqual(@PathVariable("IBAN") String IBAN, @PathVariable("amount") long amount, @RequestHeader("Authorization") String token) {
        return transactionService.transferAmountLessAndEqual(IBAN, amount, token);
    }

    @GetMapping("{IBAN}/amountGrater/{amount}")
    public List<Transaction> transferAmountGrater(@PathVariable("IBAN") String IBAN, @PathVariable("amount") long amount, @RequestHeader("Authorization") String token) {
        return transactionService.transferAmountGrater(IBAN, amount, token);
    }

    @GetMapping("{IBAN}/amountGraterEqual/{amount}")
    public List<Transaction> transferAmountGraterAndEqual(@PathVariable("IBAN") String IBAN, @PathVariable("amount") long amount, @RequestHeader("Authorization") String token) {
        return transactionService.transferAmountGraterAndEqual(IBAN, amount, token);
    }

    @PostMapping("deposit/{IBAN}/{Amount}")
    public String depoistAmount(@PathVariable("IBAN") String IBAN, @PathVariable("Amount") long Amount, @RequestHeader("Authorization") String token) {
        return transactionService.depoistMoney(IBAN, Amount, token);
    }




}
