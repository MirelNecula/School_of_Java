package PaymentSystemInterface;

public class PaymentDemo {
    public static void main(String[] args) {
        CreditCard cc = new CreditCard("1234-5678-9012-3456" , "John Doe", 500);
        cc.pay(250);
        cc.refund(40);

        System.out.println("Final CreditCard state : " + cc.getBalance());

        PayPal pp = new PayPal("vali.rulz@yahoo.com", 300);
        pp.pay(150);
        pp.refund(20);

        System.out.println("Final PayPal state on email: " + pp.getEmail() + " and value is: " + pp.getBalance());

        Crypto crypto = new Crypto("vali.rulz@yahoo.com", 800);
        crypto.pay(400);
        crypto.refund(100);

        System.out.println("Final Crypto wallet on email: " +crypto.getWalletmail() + " and value is: " + crypto.getBalance());
    }

}
