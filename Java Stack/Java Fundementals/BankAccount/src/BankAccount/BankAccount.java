package BankAccount;

public class BankAccount {
    // MEMBER VARIABLES
    private double checkingBalance;
    private double savingsBalance;
    private static int accounts;
    private static double totalMoney;
    // refers to the sum of all bank account checking and savings balances
    // CONSTRUCTOR
    // Be sure to increment the number of accounts
    public BankAccount() {
        checkingBalance = 0;
        savingsBalance = 0;
        accounts++;
    }
    // GETTERS
    // for checking, savings, accounts, and totalMoney
    public double getCheckingBalance() {
        return checkingBalance;
    }
    public double getSavingsBalance() {
        return savingsBalance;
    }
    public static int getAccounts() {
        return accounts;
    }
    public static double getTotalMoney() {
        return totalMoney;
    }
    // METHODS
    // deposit
    // - users should be able to deposit money into their checking or savings account
    public void deposit(String accountType, double amount) {

        switch (accountType) {
            case "checking":
                checkingBalance += amount;
                break;

            case "savings":
                savingsBalance += amount;
                break;

            default:
                System.out.println("Invalid account type");
                return;
        }

        totalMoney += amount;
    }
    // withdraw 
    // - users should be able to withdraw money from their checking or savings account
    // - do not allow them to withdraw money if there are insufficient funds
    // - all deposits and withdrawals should affect totalMoney
    public void withdraw(String accountType, double amount) {

        switch (accountType) {

            case "checking":
                if (checkingBalance >= amount) {
                    checkingBalance -= amount;
                    totalMoney -= amount;
                } else {
                    System.out.println("Insufficient checking funds");
                }
                break;

            case "savings":
                if (savingsBalance >= amount) {
                    savingsBalance -= amount;
                    totalMoney -= amount;
                } else {
                    System.out.println("Insufficient savings funds");
                }
                break;

            default:
                System.out.println("Invalid account type");
        }
    }
    		
    // getBalance
    // - display total balance for checking and savings of a particular bank account
    public double getBalance() {
        return checkingBalance + savingsBalance;
    }
}

