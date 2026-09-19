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

    @Override
    public void deposit(Account account, double amount) {

        if(amount <=0){
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }



            double balance = account.getBalance();
            account.setBalance(balance + amount);

    }

    @Override
    public void withdraw(Account account, double amount) {

        double currentBalance = account.getBalance();

        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than zero ");
        if (amount > currentBalance) throw new IllegalArgumentException("Insufficient Funds");

        account.setBalance(currentBalance - amount);
    }

    @Override
    public void transfer(Account sender, double amount, Account receiver) {


        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than zero ");
        if (sender == receiver) throw new IllegalArgumentException("Cant transfer funds to self");

        double currentBalance = sender.getBalance();

        if (amount > currentBalance) throw new IllegalArgumentException("Insufficient Funds");

        double receiverBalance = receiver.getBalance();

        sender.setBalance(currentBalance-amount);
        receiver.setBalance(receiverBalance + amount);


    }

    @Override
    public Account getAccountByUsername(String username) {
        Optional<Account> existedAccount = walletSystem.getAccounts().stream().
                filter(acc->acc.getUsername().equals(username)).findFirst();
        if (existedAccount.isEmpty()){
            throw new IllegalArgumentException("Account not Found");
        }


            return existedAccount.get();

    }

    @Override
    public double getBalance(Account account) {
        return account.getBalance();
    }
}
