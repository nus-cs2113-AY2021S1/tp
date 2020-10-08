package seedu.duke.data.framework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static seedu.duke.common.Messages.LINE;

public class Power {

    private final int power;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");
    LocalDateTime currentTime;
    Appliance appliance;
    private String offTime;
    private String onTime;
    private double powerUsed;
    private double totalHours;

    private Boolean isOn;
    private double totalPowerConsumption;

    public Power(String power) {
        this.power = Integer.parseInt(power);
        this.isOn = false;
        totalPowerConsumption = 0;
        totalHours = 0;
    }

    /**
     * Appliance only can be switched on if it was 'off' previously.
     * @return
     */
    public void onAppliance() {
        if (!isOn) {
            this.isOn = true;
            onTime = getCurrentTime();
        } else {
            System.out.println("The appliance is already ON previously.");
        }
    }

    public void offAppliance() {
        if (isOn) {
            this.isOn = false;
            offTime = getCurrentTime();
            try {
                calculateTimeUsed();
            } catch (ParseException e) {
                System.out.println(LINE + "Time format is wrong.");
            }
        } else {
            System.out.println("The appliance is already OFF previously.");
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
        boolean isOnOnce;

        if (onTime != null) {
            isOnOnce = true;
        } else {
            isOnOnce = false;
        }

        if (isOnOnce) {
            onTimeValue = timeFormat.parse(onTime);
            if (!this.isOn) {
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
    }

    private void calculatePowerConsumed() throws ParseException {
        calculateTimeUsed();
        powerUsed = totalHours * power / 1000.00;
        totalPowerConsumption += powerUsed;
    }

    public Boolean getStatus() {
        return this.isOn;
    }

    public double getPower() {
        try {
            calculatePowerConsumed();
        } catch (ParseException e) {
            System.out.println(LINE + "Time format is wrong.");
        }
        return this.totalPowerConsumption;
    }

    public void computeFromFile(double powerComsumption) {
        powerUsed = powerComsumption;
        totalPowerConsumption += powerUsed;
    }

    public String toString() {
        return String.valueOf(this.totalPowerConsumption);
    }

}
