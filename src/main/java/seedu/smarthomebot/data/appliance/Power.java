package seedu.smarthomebot.data.appliance;

import seedu.smarthomebot.ui.TextUi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static seedu.smarthomebot.commons.Messages.LINE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_TIME_FORMAT_ERROR;

//@@author fanceso

/**
 * Class representing the power consumption of appliances.
 */
public class Power {

    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy-HH:mm:ss";
    private final int wattage;
    private String offTime;
    private String onTime;
    private Boolean status;
    private double powerUsed;
    private double totalPowerConsumption;

    /**
     * Constructor for Power Class.
     *
     * @param wattage Wattage of appliance.
     */
    public Power(String wattage) {
        this.wattage = Integer.parseInt(wattage);
        this.status = false;
        this.totalPowerConsumption = 0.00;
    }

    /**
     * Gets the status of the appliance.
     *
     * @return the status of the appliance in Boolean.
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Appliance only can be switched on if it was 'off' previously.
     *
     * @return true if appliance has not been turned on before.
     */
    public boolean onAppliance() {
        if (!status) {
            status = true;
            onTime = getCurrentTime();
            assert !onTime.isEmpty() : "onTime must not accept empty value";
            return true;
        } else {
            // Appliance has been turned ON previously
            return false;
        }
    }

    /**
     * Appliance only can be switched off if it was 'on' previously.
     * Compute the total power consumption once appliance is off.
     *
     * @return true if appliance has not been turned off before.
     */
    public boolean offAppliance() {
        if (status) {
            status = false;
            offTime = getCurrentTime();
            computeTotalPower();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reset all the appliance power usage and the total power consumption.
     */
    public void resetPower() {
        powerUsed = 0.00;
        totalPowerConsumption = 0.00;
    }

    /**
     * Gets the power consumption of appliance.
     *
     * @return the power consumption in double.
     */
    public double getPower() {
        computeTotalPower();
        return totalPowerConsumption;
    }

    /**
     * Gets the current system time from local machine.
     *
     * @return formatted current time with given format "dd/MM/yyyy-HH:mm:ss".
     */
    private String getCurrentTime() {
        LocalDateTime currentTime;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.ENGLISH);
        currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }

    /**
     * Computes power consumption from loaded file.
     *
     * @param powerConsumption of each appliance used within a day
     */
    public void loadDataFromFile(double powerConsumption) {
        powerUsed = powerConsumption;
        totalPowerConsumption += powerUsed;
    }

    /**
     * Calculate time used while is appliance on.
     */
    private double calculateTimeUsed() throws ParseException {
        double totalHours;
        SimpleDateFormat timeFormat = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH);
        Date onTimeValue;
        Date offTimeValue;
        double timeUsed = 0;

        if (onTime != null) {
            assert !onTime.isEmpty() : "onTime must not accept empty value";
            onTimeValue = timeFormat.parse(onTime);
            if (!status) {
                offTimeValue = timeFormat.parse(offTime);
                timeUsed = offTimeValue.getTime() - onTimeValue.getTime();
                assert timeUsed >= 0 : "System Time is not correct! " + timeUsed;
                onTime = offTime;
            } else {
                Date currentUsedTime = timeFormat.parse(getCurrentTime());
                timeUsed = currentUsedTime.getTime() - onTimeValue.getTime();
                onTime = getCurrentTime();
            }
        }

        totalHours = timeUsed / (1000 * 60 * 60);
        return totalHours;
    }

    /**
     * Computes power of Appliance to latest instance of duration where Appliance is on.
     */
    private void computePower() {
        try {
            double totalTimeUsed = calculateTimeUsed();
            // Convert power unit to kWh
            powerUsed = totalTimeUsed * wattage / 1000.00;
        } catch (ParseException e) {
            TextUi ui = new TextUi();
            ui.printToUser(LINE + MESSAGE_TIME_FORMAT_ERROR);
        }
        assert powerUsed >= 0 : "Power usage cannot be negative! " + powerUsed;
    }

    /**
     * Increment the total Power consumption of the Appliance.
     */
    private void computeTotalPower() {
        computePower();
        totalPowerConsumption += Math.round(powerUsed * 100.0) / 100.0;
        assert totalPowerConsumption >= 0 : "totalPowerConsumption cannot be negative";
    }

    /**
     * Gets the power consumption in String.
     *
     * @return the power consumption with only 2 decimal places in String.
     */
    public String toString() {
        return String.format("%.2f", totalPowerConsumption);
    }

}
