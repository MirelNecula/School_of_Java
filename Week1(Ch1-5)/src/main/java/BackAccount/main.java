package BackAccount;
import  java.util.Scanner;

public class main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.setAccountNumber("123456789");
        account.setOwner("John Doe");
        account.setBalance(1000.0);
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Owner: " + account.getOwner());
        System.out.println("Balance: $" + account.getBalance());

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter deposit amount: ");
        double depositAmount = sc.nextDouble();
        account.deposit(depositAmount);
        System.out.println("New Balance after deposit: $" + account.getBalance());
        System.out.print("Enter withdraw amount: ");
        double withdrawAmount = sc.nextDouble();
        account.withdraw(withdrawAmount);
        System.out.println("New Balance after withdraw: $" + account.getBalance());
        sc.close();

    }
}
