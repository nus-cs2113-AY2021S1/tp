package seedu.duke.data.framework;

public abstract class Appliance {
    protected String name;
    protected String location;
    protected String power;
    private String status;
    private Power appliancePower;
    private static final String ON = "On";
    private static final String OFF = "Off";

    public Appliance(String name, String location, String power) {
        this.name = name;
        this.location = location;
        this.status = OFF;
        this.power = power;
        appliancePower = new Power(power);
    }

    public void switchOn() {
        appliancePower.onAppliance();
    }

    public void switchOff() {
        appliancePower.offAppliance();
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
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public String toString() {
        return this.name + ": " + getStatus();
    }

}
