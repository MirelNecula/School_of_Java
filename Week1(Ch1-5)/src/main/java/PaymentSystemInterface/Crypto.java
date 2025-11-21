package PaymentSystemInterface;

public class Crypto implements Payable , Refundable {

    private String walletmail;
    private double balance;

    public Crypto(String walletmail, double balance) {
        this.walletmail = walletmail;
        this.balance = balance;
    }

    @Override
    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Suma de plată trebuie să fie pozitivă.");
            return;
        }
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Paid " + amount + " using Crypto wallet " + walletmail + ".");
        } else {
            System.out.println("Insufficient balance in Crypto wallet " + walletmail + ".");
        }
    }
    @Override
    public void refund(double amount) {
        balance += amount;
        System.out.println("Refunded " + amount + " to Crypto wallet " + walletmail + ".");
    }
    public double getBalance() {
        return balance;
    }
    public String getWalletmail() {
        return walletmail;
    }

    @Override
    public String toString() {
        return "Crypto[" + walletmail + ", balance=" + balance + "]";
    }
}

