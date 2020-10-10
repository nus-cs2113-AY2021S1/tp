package seedu.duke.commands;

import seedu.duke.data.framework.Appliance;

import static seedu.duke.common.Messages.LINE;

public class UsageCommand extends Command {

    public static final String COMMAND_WORD = "usage";

    @Override
    public void execute() {
        double totalUsage = 0;
        System.out.print(LINE);
        int maxLengthName = 0;
        int maxLengthLocation = 0;
        boolean maxNameValue = true;
        boolean maxLocationValue = true;
        String applianceName;
        String location;
        String status;

        /** Find length of longest appliance name and location for formatting. */
        for (Appliance a : appliances.getAllAppliance()) {
            applianceName = a.getName();
            location = a.getLocation();
            maxLengthName = (maxNameValue) ? applianceName.length() : Math.max(maxLengthName, applianceName.length());
            maxNameValue = false;
            maxLengthLocation = (maxLocationValue) ? location.length() : Math.max(maxLengthLocation, location.length());
            maxLocationValue = false;
        }

        for (int i = 0; i < appliances.getAllAppliance().size(); i++) {
            status = appliances.getAppliance(i).getStatus();
            applianceName = appliances.getAppliance(i).getName();
            location = appliances.getAppliance(i).getLocation();
            double appliancePower = appliances.getAppliance(i).measureConsumption();
            totalUsage += appliancePower;
            String format = "%d. %-" + maxLengthName
                    + "s | Location: %-" + maxLengthLocation
                    + "s | Status: %s | Usage: %.2f kWh \n";
            System.out.printf(format, i + 1, applianceName, location, status, appliancePower);
        }
        System.out.format("\nTotal power consumption: %.2f kWh \n", totalUsage);
    }

}
