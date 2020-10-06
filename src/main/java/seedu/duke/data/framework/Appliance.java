package seedu.duke.data.framework;

public abstract class Appliance {

    protected String name;
    protected String location;
    protected boolean status;

    public Appliance(String name, String location) {
        this.name = name;
        this.location = location;
        this.status = false;
    }

    public void switchOn() {
        this.status = true;
    }

    public void switchOff() {
        this.status = false;
    }

    public String getStatus() {
        String status = "Off";
        if (this.status) {
            status = "On";
        }
        return status;
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
