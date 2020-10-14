public class deliveryOrder extends Order {

    private boolean isDeliveryOrder;
    private  double carrierFee;
    private double deliveryFee;
    private String address;
    private String phoneNumber;

    deliveryOrder(Canteen canteen, Stall stall, Dish dish, boolean isDeliveryOrder,
                  double carrierFee, double deliveryFee, String address, String phoneNumber) {
        super(canteen, stall, dish);
        this.isDeliveryOrder = isDeliveryOrder;
        this.carrierFee = carrierFee;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsDeliveryOrder() {
        return this.isDeliveryOrder;
    }

    public double getCarrierFee() {
        return this.carrierFee;
    }

    public double getDeliveryFee() {
        return this.deliveryFee;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

}
