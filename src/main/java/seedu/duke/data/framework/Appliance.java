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
        status = true;
    }

    public void switchOff() {
        this.status = false;
    }

    public boolean getStatus() {
        return this.status;
    }

    public abstract String getType();

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public String toString() {
        return this.name + " status: "+ getStatus();
    }

}
