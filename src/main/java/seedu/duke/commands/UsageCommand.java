package seedu.duke.commands;

public class UsageCommand extends Command {

    public static final String COMMAND_WORD = "usage";

    @Override
    public void execute() {
        double totalUsage = 0;
        for (int i = 0; i < appliances.getAllAppliance().size(); i++) {
            String applianceName = appliances.getAppliance(i).getName();
            double appliancePower = appliances.getAppliance(i).measureConsumption();
            System.out.format(applianceName + " Consumed Power: %.2f kWh \n", appliancePower);
            totalUsage += appliancePower;
            System.out.println("--------------------------------------------------------------");
        }
        System.out.format("Total power consumption: %.2f kWh \n", totalUsage);
    }

}
