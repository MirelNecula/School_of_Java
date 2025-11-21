package PaymentSystemInterface;

public class  CreditCard implements Refundable, Payable {
    private double balance;
    private String cardNumber;
    private String HolderName;


    public CreditCard(String cardNumber, String holderName, double balance) {
        this.cardNumber = cardNumber;
        this.HolderName = holderName;
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
            System.out.println("Paid " + amount + " using Credit Card " + maskedCard() + ".");
        } else {
            System.out.println("Insufficient balance on Credit Card " + maskedCard() + ".");
        }
    }

    @Override
    public void refund(double amount) {
        balance += amount;
        System.out.println("Refunded " + amount + " to Credit Card.");
    }

    public double getBalance() {
        return balance;
    }

    private String maskedCard() {
        if (cardNumber == null || cardNumber.length() < 4) return "****";
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }

    @Override
    public String toString() {
        return "CreditCard[" + HolderName + ", balance=" + balance + "]";
    }
}
