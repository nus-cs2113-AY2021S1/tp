package seedu.duke.data.framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Power {

    private final int power;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");
    LocalDateTime currentTime;
    private String offTime;
    private String onTime;
    private double powerUsed;
    private double totalHours;

    private Boolean status;
    private double totalPowerConsumption;

    public Power(String power) {
        this.power = Integer.parseInt(power);
        this.status = false;
        totalPowerConsumption = 0;
        totalHours = 0;
    }

    /**
     * Appliance only can be switched on if it was 'off' previously.
     */
    public void onAppliance() {
        if (!status) {
            status = true;
            onTime = getCurrentTime();
            //Testing
            System.out.println("On at : " + onTime);
        } else {
            System.out.println("The appliance remains its status. " + status);
        }
    }

    public void offAppliance() {
        if (status) {
            status = false;
            offTime = getCurrentTime();
            //Testing
            System.out.println("Off at : " + offTime);
            try {
                calculateTimeUsed();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The appliance remains its status. " + status);
        }
    }

    /**
     * Gets the system current time.
     *
     * @return currentTime in string with the format defined
     */
    private String getCurrentTime() {
        currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }

    private void calculateTimeUsed() throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss");
        Date onTimeValue;
        Date offTimeValue;
        double timeUsed;
        if (onTime != null) {
            onTimeValue = timeFormat.parse(onTime);
            if (!this.status) {
                offTimeValue = timeFormat.parse(offTime);
                timeUsed = offTimeValue.getTime() - onTimeValue.getTime();
                onTime = offTime;
            } else {
                Date currentUsedTime = timeFormat.parse(getCurrentTime());
                timeUsed = currentUsedTime.getTime() - onTimeValue.getTime();
                onTime = getCurrentTime();
            }
        } else {
            timeUsed = 0;
        }

        // For simulation purpose, 1 second in System equals to 1 hour in SmartHomeBot
        // Convert back to hours timeDifference/(1000 * 60 * 60)
        totalHours = timeUsed / (1000 * 6);
        System.out.format("Hours used: %.2f \n", totalHours);

    }

    private void calculatePowerConsumed() throws ParseException {
        calculateTimeUsed();
        powerUsed = totalHours * power / 1000.00;
        totalPowerConsumption += powerUsed;
        System.out.format("Power used: %.2f kWh at that instance.\n", powerUsed);
    }

    public Boolean getStatus() {
        return this.status;
    }

    public double getPower() {
        try {
            calculatePowerConsumed();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.totalPowerConsumption;
    }

    public void computeFromFile(double powerComsumption) {
        powerUsed = powerComsumption;
        totalPowerConsumption += powerUsed;
    }

    public String toString() {
        return String.valueOf(this.powerUsed);
    }

}
