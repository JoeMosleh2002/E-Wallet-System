package service;

import Model.Account;
import Model.WalletSystem;
import enums.DepositStatus;
import enums.WithdrawStatus;

import javax.crypto.DecapsulateException;
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
           System.out.println(walletSystem.getAccounts());
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
    public DepositStatus deposit(Account account, double amount) throws IllegalArgumentException {

        // 1. Make sure account exists
        // 2. Make sure amount does not exceed 12000
        // 3. min deposit is no less than 100
        // 4. Deposit functionality

        Optional <Account> existAccount = walletSystem.getAccounts().stream().filter(acc-> acc.getUsername().equals(account.getUsername())).findFirst();
        if (existAccount.isEmpty()){
            return DepositStatus.ACCOUNT_NOT_EXIST;

        }

        if(amount > 12000){
            return DepositStatus.AMOUNT_GREATER_THAN_MAX;
        }

        if (amount <= 100){
            return DepositStatus.AMOUNT_LESS_THAN_MIN;
        }



            double currentBalance = existAccount.get().getBalance();
            existAccount.get().setBalance(currentBalance + amount);
            return DepositStatus.SUCCESS;

    }

    @Override
    public WithdrawStatus withdraw(Account account, double amount) throws IllegalArgumentException {

        Optional <Account> existedAccount = walletSystem.getAccounts().stream().filter(acc-> acc.getUsername().equals(account.getUsername())).findFirst();
        double currentBalance = existedAccount.get().getBalance();

        if (existedAccount.isEmpty()) return WithdrawStatus.ACCOUNT_NOT_FOUND;
        if(amount >8000) return WithdrawStatus.EXCEEDS_MAX_LIMIT;
        if (amount <=100) return WithdrawStatus.BELOW_MIN_LIMIT;
        if (amount > currentBalance) return WithdrawStatus.INSUFFICIENT_FUNDS;


        else{
            existedAccount.get().setBalance(currentBalance - amount);
            return WithdrawStatus.SUCCESS;
        }




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
