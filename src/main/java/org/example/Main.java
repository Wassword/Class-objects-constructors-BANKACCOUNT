package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Create a sample instance of BankAccount and add it to the list
        BankAccount myAccount = new BankAccount("John Doe", 1500.50);
        accounts.add(myAccount);

        // Call the method to display account information
        myAccount.displayAccountInfo();

        while (true) {
            System.out.println("Hello! Welcome to Gringotts!");
            System.out.print("Are you an existing customer? (-1 to exit)\n1. Yes\n2. No\n");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == -1) {
                break;
            } else if (choice == 1) {
                System.out.print("Enter your account number: ");
                int accountNumber = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                BankAccount account = null;
                for (BankAccount acc : accounts) {
                    if (acc.getAccountNumber() == accountNumber) {
                        account = acc;
                        break;
                    }
                }

                if (account != null) {
                    BankAccount.mainMenu(account, accounts);
                } else {
                    System.out.println("Account not found.");
                }
            } else if (choice == 2) {
                BankAccount newAccount = new BankAccount();
                accounts.add(newAccount);
                BankAccount.mainMenu(newAccount, accounts);
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }
}