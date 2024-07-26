package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BankAccount {
    private double accountBalance;
    private String accountHolderName;
    private int accountNumber;
    private static int accountCounter = 1000;

    // Constructor with user input
    public BankAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's make a new account!");
        System.out.print("What is the name for the account? ");
        this.accountHolderName = scanner.nextLine();
        System.out.print("What is the beginning balance for the account? ");
        this.accountBalance = scanner.nextDouble();
        this.accountNumber = accountCounter++;
    }

    // Constructor with parameters
    public BankAccount(String accountHolderName, double accountBalance) {
        this.accountHolderName = accountHolderName;
        this.accountBalance = accountBalance;
        this.accountNumber = accountCounter++;
    }

    // Getter for accountHolderName
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Getter for accountBalance
    public double getAccountBalance() {
        return accountBalance;
    }

    // Getter for accountNumber
    public int getAccountNumber() {
        return accountNumber;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Deposited: $" + amount + " to " + accountHolderName);
        } else {
            System.out.println("Amount must be positive.");
        }
    }

    // Withdrawal method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Withdrawn " + amount + " from " + accountHolderName);
        } else if (amount > accountBalance) {
            System.out.println("Insufficient funds");
        } else {
            System.out.println("Amount should be a number");
        }
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Account Balance: " + accountBalance);
    }

    // Method to display account details
    public void displayAccountInfo() {
        System.out.println("Name: " + accountHolderName);
        System.out.println("Balance: " + accountBalance);
        System.out.println("Account number: " + accountNumber);
    }

    // Transfer method
    public void transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= accountBalance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println("Transferred: $" + amount + " to " + targetAccount.getAccountHolderName());
        } else if (amount > accountBalance) {
            System.out.println("Insufficient funds");
        } else {
            System.out.println("Amount should be a number");
        }
    }

    // mainMenu method
    public static void mainMenu(BankAccount account, ArrayList<BankAccount> accounts) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Gringotts!");
        while (true) {
            System.out.println("Hello " + account.getAccountHolderName() + "!");
            System.out.println("Welcome to the Main Menu, what would you like to do today?");
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdrawal");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to another account");
            System.out.println("0. To exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter the account number to transfer to: ");
                    int targetAccountNumber = sc.nextInt();
                    BankAccount targetAccount = null;
                    for (BankAccount acc : accounts) {
                        if (acc.getAccountNumber() == targetAccountNumber) {
                            targetAccount = acc;
                            break;
                        }
                    }
                    if (targetAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = sc.nextDouble();
                        account.transfer(targetAccount, transferAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting the main menu.");
                    return;
                default:
                    System.out.println("Wrong choice. Try again later.");
            }
        }
    }
}