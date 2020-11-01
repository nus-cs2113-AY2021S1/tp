import java.util.List;

public class deliveryOrder extends Order {

    private final static double carrierFee = 0.3;
    private final static double deliveryFee = 2.0;

    private boolean isDeliveryOrder;
    private String address;
    private int phoneNumber;

    deliveryOrder(Canteen canteen, Stall stall, List<Dish> dishes, Customer customer, boolean isDeliveryOrder,
                  String address, int phoneNumber) {
        super(canteen, stall, dishes, customer);
        this.isDeliveryOrder = isDeliveryOrder;
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

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String toString() {
        if (isDeliveryOrder) {
            return super.toString() + "(Delivery Order: address is " + this.address + " phone number is "
                    + this.phoneNumber + ")";
        }
        else {
            return super.toString() + "(Not Delivery Order)";
        }
    }
}