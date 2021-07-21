public class Bill {
    private double amount;
    String buyer;

    public Bill(double amount, String buyer) {
        this.amount = amount;
        this.buyer = buyer;
    }


    public double getAmount() {
        return amount;
    }

    public String getBuyer() {
        return buyer;
    }
}
