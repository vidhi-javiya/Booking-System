//payment class implement the interface
class Payment implements PaymentInterface {
    public static int payId = 51000;
    public int paymentId;
    public double amount;
    public boolean paid;
    public String paymentMethod;

    Payment() 
    {
        paymentId = payId++;
        paid = false;
        paymentMethod = "Not Selected";
    }

    //payment to cash
    public void makePayment(double amt) {
        amount = amt;
        paid = true;
        paymentMethod = "Cash"; 
        System.out.println("Payment of" + amount + " done cash");
    }

    //payment to card (method overloading)
    public void makePayment(double amt, String card) {
        amount = amt;
        paid = true;
        paymentMethod = "Card (" + card + ")"; 
        System.out.println("Payment of " + amount + "done using Card: " + card);
    }

    //payment to UPI
    public void makePayment(double amt, String upi, boolean isUPI) {
        amount = amt;
        paid = true;
        paymentMethod = "UPI (" + upi + ")"; 
        System.out.println("Payment of" + amount + " done using UPI ID: " + upi);
    }

    // ====== Cancel Payment ======
    public void cancelPayment() {
        if (paid) {
            paid = false;
            System.out.println("Payment cancelled" + amount);
        } else {
            System.out.println("No payment ");
        }
    }

    // ====== Getter Methods ======
    public boolean isPaid() {
        return paid;
    }

    public String getPaymentMethod() { 
        return paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public int getPaymentId() {
        return paymentId;
    }
}