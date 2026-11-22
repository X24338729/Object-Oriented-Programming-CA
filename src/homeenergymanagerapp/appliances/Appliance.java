package homeenergymanagerapp.appliances;
import java.io.Serializable;

/**
 * Abstract class for all appliances.
 * Holds the details every appliance has (name, room, watts, hours).
 * There is no "general appliance" so this class is abstract.
 *
 * @author Emre Cholakoglu
 */
public abstract class Appliance implements EnergyConsumer, Serializable {
    // approximate kg of CO2 for 1 kWh of electricity in Ireland (SEAI, 2025 provisional)
    public static final double CO2_PER_KWH = 0.18;
    private String name;
    private String room;
    private double watts;
    private double hoursPerDay;

    public Appliance(String name, String room, double watts, double hoursPerDay) {
        this.name = name;
        this.room = room;
        this.watts = watts;
        this.hoursPerDay = hoursPerDay;
    }

    // daily kWh = watts x hours / 1000
    @Override
    public double getDailyKWh() {
        return watts * hoursPerDay / 1000;
    }

    // monthly cost = daily kWh x 30 days x electricity rate
    public double getMonthlyCost(double rate) {
        return getDailyKWh() * 30 * rate;
    }
    // monthly CO2 in kg = daily kWh x 30 days x CO2 per kWh
    public double getMonthlyCO2() {
        return getDailyKWh() * 30 * CO2_PER_KWH;
    }
    // how much money is saved per month if the appliance is used 1 hour less each day
    public double getSavingOneHourLess(double rate) {
    if (hoursPerDay <= 1) {
        return getMonthlyCost(rate);   // used 1 hour or less, so the saving is the whole cost
    }
    double kWhPerHour = getDailyKWh() / hoursPerDay;
    return kWhPerHour * 30 * rate;
    }

    // every type of appliance gives a different tip, so the subclasses will write this
    @Override
    public abstract String getSavingTip();

    // getters
    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public double getWatts() {
        return watts;
    }

    public double getHoursPerDay() {
        return hoursPerDay;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setWatts(double watts) {
        this.watts = watts;
    }

    public void setHoursPerDay(double hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Room: " + room
             + "\nWatts: " + watts + " | Hours per day: " + hoursPerDay
             + " | Daily kWh: " + getDailyKWh();
    }
}