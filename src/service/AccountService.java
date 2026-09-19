package service;

import Model.Account;

public interface AccountService {
    Account createAccount (Account account);

    Account getAccountByUsernameAndPassword (Account account);

    void deposit (Account account , double amount);
    void withdraw (Account account, double amount);
    void transfer (Account sender,double amount, Account receiver);
    Account getAccountByUsername(String username);
    double getBalance (Account account);
}
