package seedu.smarthomebot.data.framework;

/**
 * Abstract Class representing the appliances for SmartHomeBot.
 */
public abstract class Appliance {
    private static final String ON = "ON";
    private static final String OFF = "OFF";
    private static int maxNameLength = 0;
    private static int maxLocationLength = 0;
    protected String name;
    protected String location;
    protected String power;
    protected Power appliancePower;

    public Appliance(String name, String location, String power) {
        this.name = name;
        this.location = location;
        this.power = power;
        appliancePower = new Power(power);
        if (this.name.length() > maxNameLength) {
            maxNameLength = this.name.length();
        }
        if (this.location.length() > maxLocationLength) {
            maxLocationLength = this.location.length();
        }
    }

    /**
     * Sets the status of Appliance to on.
     *
     * @return the outcome of the operation.
     */
    public boolean switchOn() {
        return appliancePower.onAppliance();
    }

    /**
     * Sets the status of Appliance to off.
     *
     * @return the outcome of the operation.
     */
    public boolean switchOff() {
        return appliancePower.offAppliance();
    }

    /**
     * Gets the status of the appliance.
     *
     * @return the status of the appliance.
     */
    public String getStatus() {
        return (appliancePower.getStatus() ? ON : OFF);
    }

    /**
     * Compute the power consumption of the appliance.
     *
     * @return the power consumption of the appliance in double.
     */
    public int measureConsumption() {
        return appliancePower.getPower();
    }

    /**
     * Gets the power consumption of the appliance in String.
     *
     * @return the power consumption of the appliance in String.
     */
    public String getPowerConsumption() {
        return appliancePower.toString();
    }

    /**
     * Recomputes the power consumption of the appliance.
     */
    public void loadConsumptionFromFile(String powerConsumption) {
        appliancePower.loadConsumptionFromFile(Integer.parseInt(powerConsumption));
    }

    /**
     * Gets the power rating of the appliance in String.
     *
     * @return the power rating of the appliance in String.
     */
    public String getPower() {
        return this.power;
    }

    /**
     * Abstract method to gets the type of appliance.
     *
     * @return the type of the appliance in String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Abstract method to gets the type of appliance.
     *
     * @return the type of the appliance in String.
     */
    public String getLocation() {
        return this.location;
    }


    /**
     * Gets the longest length of name in the appliance class.
     *
     * @return the length of the longest name of appliance in integer.
     */
    public static int getMaxNameLength() {
        return maxNameLength;
    }

    /**
     * Gets the longest length of name in the location.
     *
     * @return the length of the longest name of location in integer.
     */
    public static int getMaxLocationLength() {
        return maxLocationLength;
    }

    /**
     * Abstract method to gets the type of appliance.
     *
     * @return the type of the appliance in String.
     */
    public abstract String getType();

    public String toString() {
        return  this.getName() + "(" + this.getPower() + "W)" + " in " + this.getLocation();
    }

    public String writeFileFormat() {
        return this.location + "|" + this.name + "|" + this.power + "|"  + this.getType() + "|"
                  + this.getStatus() + "|" + this.getPowerConsumption();
    }

}
