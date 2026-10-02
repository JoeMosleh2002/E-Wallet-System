package service;

import Model.Account;
import Model.WalletSystem;
import enums.DepositStatus;
import enums.TransferStatus;
import enums.WithdrawStatus;
import java.util.Optional;

public class AccountServiceImpl implements AccountService{
    private WalletSystem walletSystem = new WalletSystem();


    @Override
    public Account createAccount(Account account) {

        boolean isAccountExistWithSameUser =
                walletSystem.getAccounts()
                        .stream()
                        .anyMatch(acc ->
                                acc.getUsername().equals(account.getUsername()));

        boolean isAccountExistWithSameNumber =
                walletSystem.getAccounts()
                        .stream()
                        .anyMatch(acc ->
                                acc.getPhoneNumber().equals(account.getPhoneNumber()));

        if (isAccountExistWithSameUser || isAccountExistWithSameNumber) {
            return null;
        }

        walletSystem.getAccounts().add(account);
        return account;
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

        if (amount < 100){
            return DepositStatus.AMOUNT_LESS_THAN_MIN;
        }



            double currentBalance = existAccount.get().getBalance();
            existAccount.get().setBalance(currentBalance + amount);
            return DepositStatus.SUCCESS;

    }

    @Override
    public WithdrawStatus withdraw(Account account, double amount) throws IllegalArgumentException {

        Optional <Account> existedAccount = walletSystem.getAccounts().stream().filter(acc-> acc.getUsername().equals(account.getUsername())).findFirst();

        if (existedAccount.isEmpty()) return WithdrawStatus.ACCOUNT_NOT_FOUND;
        double currentBalance = existedAccount.get().getBalance();
        if(amount >8000) return WithdrawStatus.EXCEEDS_MAX_LIMIT;
        if (amount <100) return WithdrawStatus.BELOW_MIN_LIMIT;
        if (amount > currentBalance) return WithdrawStatus.INSUFFICIENT_FUNDS;


        else{
            existedAccount.get().setBalance(currentBalance - amount);
            return WithdrawStatus.SUCCESS;
        }




    }

    @Override
    public TransferStatus transfer(Account sender, double amount, Account receiver) {

        Optional<Account> existedSender = walletSystem.getAccounts()
                .stream()
                .filter(acc -> acc.getUsername().equals(sender.getUsername()))
                .findFirst();

        if (existedSender.isEmpty()) {
            return TransferStatus.SENDER_NOT_FOUND;
        }

        Optional<Account> existedReceiver = walletSystem.getAccounts()
                .stream()
                .filter(acc -> acc.getUsername().equals(receiver.getUsername()))
                .findFirst();

        if (existedReceiver.isEmpty()) {
            return TransferStatus.RECEIVER_NOT_FOUND;
        }

        if (amount <= 0) {
            return TransferStatus.INVALID_AMOUNT;
        }

        Account storedSender = existedSender.get();
        Account storedReceiver = existedReceiver.get();

        if (storedSender.getUsername().equals(storedReceiver.getUsername())) {
            return TransferStatus.SAME_ACCOUNT;
        }

        double senderBalance = storedSender.getBalance();

        if (amount > senderBalance) {
            return TransferStatus.INSUFFICIENT_FUNDS;
        }

        storedSender.setBalance(senderBalance - amount);
        storedReceiver.setBalance(storedReceiver.getBalance() + amount);

        return TransferStatus.SUCCESS;
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
