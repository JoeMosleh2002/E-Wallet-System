package service;

import Model.Account;
import enums.DepositStatus;
import enums.TransferStatus;
import enums.WithdrawStatus;

public interface AccountService {
    Account createAccount (Account account);

    Account getAccountByUsernameAndPassword (Account account);

    DepositStatus deposit (Account account , double amount);
    WithdrawStatus withdraw (Account account, double amount);
    TransferStatus transfer (Account sender, double amount, Account receiver);
    Account getAccountByUsername(String username);
    double getBalance (Account account);
}
