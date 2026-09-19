package service;

import Model.Account;
import Model.WalletSystem;

import javax.swing.*;
import java.util.Scanner;


public class AppServiceImpl implements AppService {

    AccountServiceImpl accountService = new AccountServiceImpl();

    private Scanner s = new Scanner(System.in);

    @Override
    public void start()
    {
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

                if (counter== 4) System.out.println("Please contact admin");


            } catch (Exception e) {
                System.out.println("Invalid input");
                s.next(); // Consumes the bad token so Scanner doesn't loop infinitely
            }
        }
    }

    private  void login() {

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
    private void signup(){
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

    private void mainProfile(Account account){
        System.out.println("---- Choose one of the Servicess :------");
        System.out.println("1.Deposit   2.Withdraw     3. Transfer   4. Show Balance   5. Show Details  6. Change Password    7. Logout  ");
        int choose = s.nextInt();

        switch (choose) {
            case (1):
                System.out.println("Please enter amount to deposit: ");
                if (s.hasNextDouble()) {
                    double amount = s.nextDouble();

                    try {
                        accountService.deposit(account, amount);
                        System.out.println("Deposit successful");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    s.next();
                }

            case (2):
                if (s.hasNextDouble()) {
                    double amount = s.nextDouble();

                    try {
                        accountService.withdraw(account, amount);
                        System.out.println("Withdraw Successful");
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                } else {
                    System.out.println("Please enter a valid number");
                    s.next();
                }

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

            case 4 :
                System.out.println("Balance available: " + accountService.getBalance(account));




            case 5 :     System.out.println("\n----- Account Details -----");
                System.out.println("Username: " + account.getUsername());
                System.out.println("Email: " + account.getEmail());
                System.out.println("Phone Number: " + account.getPhoneNumber());
                System.out.println("Age: " + account.getAge());
                System.out.println("Balance: " + account.getBalance());
                break;





        }


    }
}