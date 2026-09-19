package service;

import Model.Account;
import Model.WalletSystem;

import java.util.Optional;

public class AccountServiceImpl implements AccountService{
    private WalletSystem walletSystem = new WalletSystem();
    @Override
    public Account createAccount(Account account) {
       boolean isAccountExistwithSameUser =  walletSystem.getAccounts().stream().anyMatch(acc->acc.getUsername().equals(account.getUsername()));
       if (isAccountExistwithSameUser){
           return null;
       }
       else {
           walletSystem.getAccounts().add(account);
           return account;
       }
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {

        Optional<Account> existedAccount = walletSystem.getAccounts().stream().filter(acc -> acc.getUsername().equals(account.getUsername())
        && acc.getPassword().equals(account.getPassword())).findFirst();

        if (existedAccount.isPresent()){
            return existedAccount.get();
        }

        else return null;
    }
}
