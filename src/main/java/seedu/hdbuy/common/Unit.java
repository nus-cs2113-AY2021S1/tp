package seedu.hdbuy.common;

public class Unit {

    private final String location;
    private final String type;
    private final int price;
    private final int leaseValue;
    private final String lease;
    private final String address;
    private final int id;

    public Unit(String location, String type, int price, int leaseValue, String lease, String address, int id) {
        this.location = location;
        this.type = type;
        this.price = price;
        this.leaseValue = leaseValue;
        this.lease = lease;
        this.address = address;
        this.id = id;
    }

    /**
     * Return the location of the Unit object.
     *
     * @return String of the location of the Unit object.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Return the type of the Unit object.
     *
     * @return String of the type of the Unit object.
     */
    public String getType() {
        return type;
    }

    /**
     * Return the price of the Unit object.
     *
     * @return The integer value of the price of the Unit object.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Return the address of the Unit object.
     *
     * @return String of the address of the Unit object.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Return the ID of the Unit object.
     *
     * @return The integer value of the ID of the Unit object.
     */
    public int getId() {
        return id;
    }

    /**
     * Return the lease value of the Unit object.
     *
     * @return The integer value of the lease value of the Unit object.
     */
    public int getLeaseValue() {
        return leaseValue;
    }

    /**
     * Return if the Unit object is leashed.
     *
     * @return String if the Unit object is leashed.
     */
    public String getLease() {
        return lease;
    }

    /**
     * This method merged the data of the Unit object into a line of String.
     *
     * @return String that consists the Unit object's metadata.
     */
    public String encodeToText() {
        return String
            .format("%d:%s:%s:%d:%s:%d:%s", getId(), getAddress(), getType(), getLeaseValue(), getLease(), getPrice(),
                getLocation());
    }

    @Override public String toString() {
        return "Address: " + getAddress() + " - Type: " + getType() + " - Lease: " + getLease() + " -  Price: $"
            + getPrice();
    }
}
