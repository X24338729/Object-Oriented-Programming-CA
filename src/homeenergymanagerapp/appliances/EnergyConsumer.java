package homeenergymanagerapp.appliances;

/**
 * Interface for anything that uses energy.
 * Any class that implements this must be able to calculate
 * its daily energy use and give a saving tip.
 *
 * @author Emre Cholakoglu
 */
public interface EnergyConsumer {

    // returns how many kWh are used in one day
    double getDailyKWh();

    // returns a saving tip for this type of appliance
    String getSavingTip();
}