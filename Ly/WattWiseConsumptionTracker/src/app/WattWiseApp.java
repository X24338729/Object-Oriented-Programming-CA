package app;

import consumption.ConsumptionTrackerFrame;
import javax.swing.SwingUtilities;

public class WattWiseApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConsumptionTrackerFrame().setVisible(true);
        });
    }
}
