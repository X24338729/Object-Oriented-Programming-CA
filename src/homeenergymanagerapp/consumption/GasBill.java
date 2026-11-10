package consumption;

import java.time.YearMonth;

public class GasBill extends EnergyBill {
    public static final String TYPE_NAME = "Gas";
    private static final double KWH_PER_CUBIC_METRE = 11.0;

    public GasBill(int id, YearMonth month, double cubicMetresUsed, double pricePerKwh) {
        super(id, TYPE_NAME, month, cubicMetresUsed, pricePerKwh);
    }

    @Override
    public String getUnit() {
        return "m3";
    }

    // Gas is measured in cubic metres but priced per kWh, so convert first.
    @Override
    public double calculateCost() {
        return getUsage() * KWH_PER_CUBIC_METRE * getUnitPrice();
    }
}
