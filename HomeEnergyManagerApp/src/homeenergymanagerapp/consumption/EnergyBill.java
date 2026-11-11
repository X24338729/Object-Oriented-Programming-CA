package consumption;

import common.EnergyRecord;
import java.time.YearMonth;

// Parent class for one monthly bill. Electricity and gas bills extend this.
public abstract class EnergyBill extends EnergyRecord {
    private final YearMonth month;
    private final double usage;      // kWh for electricity, m3 for gas
    private final double unitPrice;  // price per kWh

    public EnergyBill(int id, String typeName, YearMonth month, double usage, double unitPrice) {
        super(id, typeName);
        this.month = month;
        this.usage = usage;
        this.unitPrice = unitPrice;
    }

    public YearMonth getMonth() {
        return month;
    }

    public double getUsage() {
        return usage;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    // Each child class says what unit its usage is measured in.
    public abstract String getUnit();

    // Each child class works out its own cost (this is the polymorphism part).
    public abstract double calculateCost();

    @Override
    public String getSummary() {
        return getName() + " " + month + ": " + String.format("%.2f", calculateCost());
    }
}
