package consumption;

import java.time.YearMonth;

public class ElectricityBill extends EnergyBill {
    public static final String TYPE_NAME = "Electricity";
    private static final double STANDING_CHARGE = 3.50;

    public ElectricityBill(int id, YearMonth month, double kwhUsed, double pricePerKwh) {
        super(id, TYPE_NAME, month, kwhUsed, pricePerKwh);
    }

    @Override
    public String getUnit() {
        return "kWh";
    }

    // Electricity cost: kWh used times the price, plus a fixed standing charge.
    @Override
    public double calculateCost() {
        return getUsage() * getUnitPrice() + STANDING_CHARGE;
    }
}
