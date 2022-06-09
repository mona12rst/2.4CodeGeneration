package com.example.demo.transection;


import com.example.demo.accounts.Account;
import com.example.demo.accounts.AccountRepository;
import com.example.demo.enums.AccountStatusEnum;
import com.example.demo.enums.UserStatusEnum;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;


    public String makeTransaction(TransactionDTO transaction, String userName) {
        if(transaction.getTransferAmount() <= 0){
            return "Amount should be grater than zero";
        }
        boolean transactionAllowed = false;
        Optional<User> user = userRepository.findByUserName(userName);
        Account userAccount = null;
        if (user.isPresent() && user.get().getStatus().toString().equalsIgnoreCase("ACTIVE")) {
            if (user.get().getAccounts().size() > 0) {
                for (Account account : user.get().getAccounts()) {
                    if (account.getIBANNo().equals(transaction.senderIBAN)) {
                        if (account.getAccountStatus().toString().equalsIgnoreCase("ACTIVE")) {
                            userAccount = account;
                            transactionAllowed = true;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            } else {
                return "You don't have Account";
            }
            Optional<Account> receiverAccount = accountRepository.findByIBANNo(transaction.getReceiverIBAN());
            if (receiverAccount.isEmpty() || !receiverAccount.get().getAccountStatus().toString().equalsIgnoreCase("active")) {
                return "Sorry You can't transfer money to " + transaction.getReceiverIBAN() + " IBAN. No such account existed";
            }

            if (transactionAllowed) {
                // transfer Amount should be less the transaction limit
                if (transaction.getTransferAmount() <= userAccount.getTransactionLimit()) {
                    // Always Respect Absolute Limit
                    if ((userAccount.getBalance() - transaction.getTransferAmount()) > userAccount.getAbsoluteLimit()) {
                        long totalTransactionsOfTheDay = transactionRepository.findAllByDateOfTransaction(LocalDate.now()).stream().mapToLong(Transaction::getTransferAmount).sum();
                        // Always Respect Daily Limit
                        if ((totalTransactionsOfTheDay + transaction.getTransferAmount()) < userAccount.getDayLimit()) {

                            receiverAccount.get().setBalance(receiverAccount.get().getBalance() + transaction.getTransferAmount());
                            accountRepository.save(receiverAccount.get());

                            userAccount.setBalance(userAccount.getBalance() - transaction.transferAmount);
                            accountRepository.save(userAccount);

                            transactionRepository.save(Transaction.builder().userId(userAccount.getId())
                                    .fromIban(userAccount.getIBANNo())
                                    .toIban(transaction.getReceiverIBAN())
                                    .transferAmount(transaction.getTransferAmount())
                                    .dateOfTransaction(LocalDate.now()).build());
                            return "Done";
                        } else {
                            return "You are exceeding Daily limit";
                        }
                    } else {
                        return "You are exceeding Absolute limit";
                    }
                } else {
                    return "You are exceeding Transaction limit";
                }
            }
            return "You are not allowed to make Transaction with this IBAN";

        } else {
            return "No user found";
        }
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    // for why would anyone want to update transaction? -_- weird
    public String updateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        return "Done";
    }

    public List<Transaction> findTransactionByIban(String IBAN, String token) {
        if (validateUser(token, IBAN))
            return transactionRepository.findAllByFromIban(IBAN);
        else return null;
    }

    // ure not supposed to delete though... -_-
    public String deleteTransaction(int id) {
        transactionRepository.deleteById(id);
        return "Done";
    }

    public List<Transaction> findByFromIbanAndDateOfTransactionBetween(String IBAN, String to, String from, String token) {
        if (validateUser(token, IBAN)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return transactionRepository.findByFromIbanAndDateOfTransactionBetween(IBAN, LocalDate.parse(to, formatter), LocalDate.parse(from, formatter));
        } else
            return null;
    }

    public List<Transaction> findFromIban(String IBAN, String token) {
        if (validateUser(token, IBAN))
            return transactionRepository.findAllByFromIban(IBAN);
        else
            return null;
    }

    public List<Transaction> findToIban(String IBAN, String token, String reciverIBAN) {
        if (validateUser(token, IBAN))
            return transactionRepository.findAllByToIban(reciverIBAN);
        else
            return null;
    }

    public List<Transaction> transferAmountEquals(String IBAN, long amount, String token) {
        if (validateUser(token, IBAN))
            return transactionRepository.findAllByTransferAmountEquals(amount);
        else
            return null;
    }

    public List<Transaction> transferAmountLess(String IBAN, long amount, String token) {
        if (validateUser(token, IBAN))
            return transactionRepository.findAllByTransferAmountLessThan(amount);
        else
            return null;
    }

    public List<Transaction> transferAmountLessAndEqual(String IBAN, long amount, String token) {
        if (validateUser(token, IBAN))
            return transactionRepository.findAllByTransferAmountIsLessThanEqual(amount);
        else
            return null;
    }

    public List<Transaction> transferAmountGrater(String IBAN, long amount, String token) {
        if (validateUser(token, IBAN))
            return transactionRepository.findAllByTransferAmountGreaterThan(amount);
        else
            return null;
    }

    public List<Transaction> transferAmountGraterAndEqual(String IBAN, long amount, String token) {
        if (validateUser(token, IBAN))
            return transactionRepository.findAllByTransferAmountIsGreaterThanEqual(amount);
        else
            return null;
    }

    public String depoistMoney(String IBAN, long amount, String token) {
        if (validateUser(token, IBAN)) {
            Optional<Account> a = accountRepository.findByIBANNo(IBAN);
            a.ifPresent(account -> account.setBalance(account.getBalance() + amount));
            accountRepository.save(a.get());
            return "Done";
        } else
            return "Sorry this is not your IBAN";
    }

    private boolean validateUser(String token, String IBAN) {
        String username = jwtUtil.extractUsername(token.substring(7));
        Optional<User> user = userRepository.findByUserName(username);
        if (user.isPresent()) {
            for (Account account : user.get().getAccounts()) {
                if (account.getIBANNo().equals(IBAN)) {
                    return true;
                }
            }
        }
        return false;
    }

}
