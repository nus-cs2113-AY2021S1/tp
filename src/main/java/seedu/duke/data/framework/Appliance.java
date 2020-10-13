package seedu.duke.data.framework;

public abstract class Appliance {
    private static final String ON = "On";
    private static final String OFF = "Off";
    private static int maxNameLength;
    private static int maxLocationLength;
    protected String name;
    protected String location;
    protected String power;
    private String status;
    private Power appliancePower;

    public Appliance(String name, String location, String power) {
        this.name = name;
        this.location = location;
        this.status = OFF;
        this.power = power;
        appliancePower = new Power(power);
        maxLocationLength = 0;
        maxNameLength = 0;
    }

    public static int getMaxNameLength() {
        return maxNameLength;
    }

    public static int getMaxLocationLength() {
        return maxLocationLength;
    }

    public boolean switchOn() {
        return appliancePower.onAppliance();
    }

    public boolean switchOff() {
        return appliancePower.offAppliance();
    }

    public String getStatus() {
        if (appliancePower.getStatus()) {
            this.status = ON;
        } else {
            this.status = OFF;
        }
        return this.status;
    }

    public String getStringPower() {
        return power;
    }

    public double measureConsumption() {
        return appliancePower.getPower();
    }

    public String getPowerConsumption() {
        return appliancePower.toString();
    }

    public void updatePowerConsumption(String powerConsumption) {
        appliancePower.computeFromFile(Double.parseDouble(powerConsumption));
    }

    public String getPower() {
        return this.power;
    }

    public abstract String getType();

    public String getName() {
        if (this.name.length() > maxNameLength) {
            maxNameLength = this.name.length();
        }
        return this.name;
    }

    public String getLocation() {
        if (this.location.length() > maxLocationLength) {
            maxLocationLength = this.location.length();
        }
        return this.location;
    }

    public String toString() {
        return this.name + ": " + getStatus();
    }

}
