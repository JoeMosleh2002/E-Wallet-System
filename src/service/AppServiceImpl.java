package service;

import Model.Account;
import Model.WalletSystem;
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
                        System.out.println("Login Features");
                        break;


                    case 2:
                        System.out.println("\n--- Signup ---");
                        System.out.print("Enter username: ");
                        String regUsername = s.next();

                        System.out.print("Enter password: ");
                        String regPassword = s.next();



                        // Create the account object inline
                        Account newAccount = new Account(regUsername, regPassword);
                        accountService.createAccount(newAccount);

                        System.out.println("Account created successfully for: " + newAccount.getUsername());
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
}