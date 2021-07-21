public class Order {
    private int quantity;
    private double unitPrice;
    private String buyer;


    public Order(double unitPrice, int quantity, String buyer) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.buyer = buyer;
    }

    public double getTotalAmount() {
        return unitPrice * quantity;
    }

    public String getBuyer() {
        return buyer;
    }
}
