package PaymentSystemInterface;

public class PayPal implements Payable , Refundable {
    String email;
    double balance;

    public PayPal(String email, double balance) {
        this.email = email;
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
            System.out.println("Paid " + amount + " using PayPal account " + email + ".");
        } else {
            System.out.println("Insufficient balance in PayPal account " + email + ".");
        }
    }

    @Override
    public void refund(double amount) {
        balance += amount;
        System.out.println("Refunded " + amount + " to PayPal account " + email + ".");
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "PayPal[" + email + ", balance=" + balance + "]";
    }
}
