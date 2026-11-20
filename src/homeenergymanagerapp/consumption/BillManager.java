package consumption;

import common.FileSavable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BillManager implements FileSavable {
    private final ArrayList<EnergyBill> bills = new ArrayList<>();
    private final Path filePath = Paths.get("bills.txt");
    private int nextId = 1;

    public BillManager() {
        try {
            loadFromFile();
        } catch (IOException ex) {
            System.err.println("Could not load bills: " + ex.getMessage());
        }
    }

    public List<EnergyBill> getAll() {
        return new ArrayList<>(bills);
    }

    public EnergyBill add(String typeName, YearMonth month, double usage, double unitPrice) {
        EnergyBill bill;
        if (ElectricityBill.TYPE_NAME.equals(typeName)) {
            bill = new ElectricityBill(nextId, month, usage, unitPrice);
        } else if (GasBill.TYPE_NAME.equals(typeName)) {
            bill = new GasBill(nextId, month, usage, unitPrice);
        } else {
            throw new IllegalArgumentException("Unknown bill type: " + typeName);
        }
        nextId++;
        bills.add(bill);
        return bill;
    }

    public EnergyBill findById(int id) {
        for (EnergyBill bill : bills) {
            if (bill.getId() == id) {
                return bill;
            }
        }
        return null;
    }

    public List<EnergyBill> searchByMonth(YearMonth month) {
        List<EnergyBill> results = new ArrayList<>();
        for (EnergyBill bill : bills) {
            if (bill.getMonth().equals(month)) {
                results.add(bill);
            }
        }
        return results;
    }

    public boolean delete(int id) {
        EnergyBill bill = findById(id);
        if (bill == null) {
            return false;
        }
        bills.remove(bill);
        return true;
    }

    @Override
    public void saveToFile() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (EnergyBill bill : bills) {
                writer.write(bill.getName() + "|" + bill.getId() + "|" + bill.getMonth() + "|"
                        + bill.getUsage() + "|" + bill.getUnitPrice());
                writer.newLine();
            }
        }
    }

    @Override
    public void loadFromFile() throws IOException {
        bills.clear();
        if (!Files.exists(filePath)) {
            return;
        }

        int largestId = 0;
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                EnergyBill bill = parseLine(line);
                if (bill != null) {
                    bills.add(bill);
                    if (bill.getId() > largestId) {
                        largestId = bill.getId();
                    }
                }
            }
        }
        nextId = largestId + 1;
    }

    // A saved line looks like: Electricity|1|2026-10|300.0|0.3
    private EnergyBill parseLine(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length != 5) {
            return null;
        }

        try {
            String typeName = parts[0];
            int id = Integer.parseInt(parts[1]);
            YearMonth month = YearMonth.parse(parts[2]);
            double usage = Double.parseDouble(parts[3]);
            double unitPrice = Double.parseDouble(parts[4]);

            if (ElectricityBill.TYPE_NAME.equals(typeName)) {
                return new ElectricityBill(id, month, usage, unitPrice);
            }
            if (GasBill.TYPE_NAME.equals(typeName)) {
                return new GasBill(id, month, usage, unitPrice);
            }
            return null;
        } catch (NumberFormatException | DateTimeParseException ex) {
            // Skip a bad line instead of stopping the whole load.
            return null;
        }
    }
}
