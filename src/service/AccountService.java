package service;

import Model.Account;

public interface AccountService {
    Account createAccount (Account account);

    Account getAccountByUsernameAndPassword (Account account);
}
