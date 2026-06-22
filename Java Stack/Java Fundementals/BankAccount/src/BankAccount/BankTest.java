package BankAccount;


public class BankTest {
    public static void main(String[] args){
        // Create 3 bank accounts
        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount();
        BankAccount acc3 = new BankAccount();
        // Deposit Test
        // - deposit some money into each bank account's checking or savings account and display the balance each time
        // - each deposit should increase the amount of totalMoney
        acc1.deposit("checking", 1000);
        acc1.deposit("savings", 1500);
        acc2.deposit("checking", 800);
        acc2.deposit("savings", 500);
        acc3.deposit("checking", 100);
        acc3.deposit("savings", 50);
        System.out.println("Account 1 Balance :" + acc1.getBalance());
        System.out.println("Account 2 Balance :" + acc2.getBalance());
        System.out.println("Account 3 Balance :" + acc3.getBalance());
        
        
        
        // Withdrawal Test
        // - withdraw some money from each bank account's checking or savings account and display the remaining balance
        // - each withdrawal should decrease the amount of totalMoney
        acc1.withdraw("savings", 1000);
        acc2.withdraw("checking", 300);
        acc3.withdraw("checking",50);
        System.out.println("Account 1 Balance is:"  + acc1.getBalance());
        System.out.println("Account 2 Balance is:"  + acc2.getBalance());
        System.out.println("Account 3 Balance is:"  + acc3.getBalance());
        // Static Test (print the number of bank accounts and the totalMoney)
        System.out.println("Total Accounts: " + BankAccount.getAccounts());
        System.out.println("Total Money: $" + BankAccount.getTotalMoney());

    }
}

