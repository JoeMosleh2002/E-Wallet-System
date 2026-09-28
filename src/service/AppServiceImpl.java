package service;

import Model.Account;
import Model.WalletSystem;
import enums.DepositStatus;
import enums.WithdrawStatus;

import javax.swing.*;
import java.util.Scanner;





public class AppServiceImpl implements AppService {

    AccountServiceImpl accountService = new AccountServiceImpl();

    private Scanner s = new Scanner(System.in);

    @Override
    public void start() {
        int counter = 0; // Keep if you need it later, or remove if unused

        while (true) {
            // Moved inside so user sees choices every iteration
            System.out.println("\n------------ Welcome to " + WalletSystem.name + " ------------------");
            System.out.println("Please choose action :");
            System.out.println("1. Login   2. Signup   3. Exit");

            try {
                int choice = s.nextInt();
                switch (choice) {
                    case 1:
                        login();
                        break;


                    case 2:
                        signup();
                        break;


                    case 3:
                        System.out.println("Bye have a great time");
                        s.close();
                        return; // Exits start() method cleanly (terminates loop)
                    default:
                        System.out.println("Please select a number between 1 and 3.");
                        counter++;
                        break;


                }


            } catch (Exception e) {
                System.out.println("Invalid input");
                counter++;
                s.next(); // Consumes the bad token so Scanner doesn't loop infinitely
            }

            if (counter >= 4) System.out.println("Please contact admin");

        }
    }

    private void login() {

        System.out.println("\n--- Login ---");
        System.out.print("Enter username: ");
        String Username = s.next();

        System.out.print("Enter password: ");
        String Password = s.next();


        Account account = new Account(Username, Password);
        Account existedAccount = accountService.getAccountByUsernameAndPassword(account);
        if (existedAccount == null) System.out.println("Account does not exist.please check credentials ");


        else {
            System.out.println("Successful Login");
            mainProfile(existedAccount);
        }

    }

    // sign up function
    private void signup() {
        System.out.println("\n--- Signup ---");
        System.out.print("Enter username: ");
        String regUsername = s.next();

        System.out.print("Enter password: ");
        String regPassword = s.next();

        System.out.print("Enter age: ");
        int age = s.nextInt();

        System.out.print("Enter email: ");
        String email = s.next();

        System.out.print("Enter phone number: ");
        String phoneNumber = s.next();


        // Create the account object inline
        Account newAccount = new Account(regUsername, regPassword, age, email, phoneNumber);
        Account createdAccount = accountService.createAccount(newAccount);

        if (createdAccount == null) {
            System.out.println("Account failed to create, username already exists.");
            return;
        }

        System.out.println("Account created successfully");
        mainProfile(createdAccount);
    }

    private void mainProfile(Account account) {
        int invaildCounter = 0;

        while (true) {
            System.out.println("---- Choose one of the Servicess :------");
            System.out.println("1.Deposit   2.Withdraw     3. Transfer   4. Show Balance   5. Show Details  6. Change Password    7. Logout  ");
            try {
                int choose = s.nextInt();

                boolean isExit = false;

                switch (choose) {
                    case (1):
                        deposit(account);
                        break;


                    case (2):

                        withdraw(account);

                    case 3:
                        System.out.println("Please enter account to transfer:");
                        String username = s.next();

                        try {
                            Account receiver = accountService.getAccountByUsername(username);

                            System.out.println("Enter amount to transfer:");

                            if (s.hasNextDouble()) {
                                double amount = s.nextDouble();

                                accountService.transfer(account, amount, receiver);
                                System.out.println("Amount transferred successfully");
                            } else {
                                System.out.println("Please enter a valid number");
                                s.next();
                            }

                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        showBalance(account);
                        break;


                    case 5:
                        System.out.println("\n----- Account Details -----");
                        System.out.println("Username: " + account.getUsername());
                        System.out.println("Email: " + account.getEmail());
                        System.out.println("Phone Number: " + account.getPhoneNumber());
                        System.out.println("Age: " + account.getAge());
                        System.out.println("Balance: " + account.getBalance());
                        break;


                    case 7:
                        System.out.println("Bye have a great time !");
                        isExit = true;
                        break;


                    default:
                        System.out.println("Please enter a vaild number");
                        invaildCounter++;


                }


                if (isExit) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid choice");
                invaildCounter++;
                s.next();


            }

            if (invaildCounter >= 4) {

                System.out.println("Please contact admin");
                return;

            }


        }


    }


    private void deposit(Account account) {

        System.out.println("Please enter amount to deposit: ");

        if (s.hasNextDouble()) {
            double amount = s.nextDouble();

            try {
                DepositStatus balance = accountService.deposit(account, amount);

                if (balance ==DepositStatus.ACCOUNT_NOT_EXIST ){
                    System.out.println("Account does not exist ");
                }

                else if (balance == DepositStatus.AMOUNT_GREATER_THAN_MAX || balance == DepositStatus.AMOUNT_LESS_THAN_MIN){
                    System.out.println("Deposit failed: Deposit amount must e between 100 and 12000");

                }

                else {
                    System.out.println("Deposit Successful ! Balance is " + balance);
                }


            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Please enter a valid number.");
            s.next();
        }


    }

    private void showBalance(Account account) {
        account = accountService.getAccountByUsernameAndPassword(account);
        System.out.println("Current Balance : " + account.getBalance());

    }


    private void withdraw(Account account) {
        if (s.hasNextDouble()) {
            double amount = s.nextDouble();

            try {
               WithdrawStatus balance = accountService.withdraw(account, amount);

               if (balance == WithdrawStatus.ACCOUNT_NOT_FOUND) System.out.println("Account does not exist ");
               else if (balance == WithdrawStatus.EXCEEDS_MAX_LIMIT || balance == WithdrawStatus.BELOW_MIN_LIMIT) System.out.println("Withdraw amount must be between 100 and 8000");
               else if (balance == WithdrawStatus.INSUFFICIENT_FUNDS) System.out.println("Insufficent funds");

               else {
                   System.out.println("Withdraw Successful! current balance is " + balance);
               }





            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());


            }

        } else {
            System.out.println("Please enter a valid number");
            s.next();
        }
    }

}