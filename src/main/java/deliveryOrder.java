import java.util.List;

public class deliveryOrder extends Order {

    private final static double carrierFee = 0.3;
    private final static double deliveryFee = 2.0;

    private boolean isDeliveryOrder;


    deliveryOrder(Canteen canteen, Stall stall, List<Dish> dishes, Customer customer,
                  boolean isDeliveryOrder) {
        super(canteen, stall, dishes, customer);
        this.isDeliveryOrder = isDeliveryOrder;

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



    @Override
    public String toString() {
        if (isDeliveryOrder) {
            return super.toString() + "(Delivery Order)";
        }
        else {
            return super.toString() + "(Not Delivery Order)";
        }
    }
}