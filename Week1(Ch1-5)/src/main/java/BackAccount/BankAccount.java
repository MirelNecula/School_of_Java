package BackAccount;

public class BankAccount {

    private String accountNumber;
    private String owner ;
    private double balance;

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getOwner() {
        return this.owner;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setAccountNumber(String accountNumber) {
        if (accountNumber != null && !accountNumber.isEmpty()) {
            this.accountNumber = accountNumber;
        } else {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
    }

    public void setOwner(String owner) {
        if(owner != null && !owner.isEmpty()) {
            this.owner = owner;
        } else {
            throw new IllegalArgumentException("Owner cannot be null or empty");
        }
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }
    public void withdraw(double amount ) {
        if ( amount >= 0 ) {
            this.balance -= amount;
        }else  {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
    }
}
