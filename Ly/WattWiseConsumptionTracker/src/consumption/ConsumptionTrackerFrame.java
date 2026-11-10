package consumption;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConsumptionTrackerFrame extends JFrame {
    private final BillManager manager = new BillManager();
    private YearMonth shownMonth; // null means show every bill
    private final JFrame menu;    // the main menu that opened this window, or null if it runs alone

    private final JComboBox<String> typeBox = new JComboBox<>(
            new String[]{ElectricityBill.TYPE_NAME, GasBill.TYPE_NAME});
    private final JTextField monthField = new JTextField();
    private final JTextField usageField = new JTextField();
    private final JTextField priceField = new JTextField();
    private final JTextField searchField = new JTextField(10);

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"ID", "Type", "Month", "Usage", "Unit", "Price per kWh", "Cost"},
            0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable table = new JTable(tableModel);

    // Runs on its own, with no menu behind it.
    public ConsumptionTrackerFrame() {
        this(null);
    }

    // The menu is passed in so the Return to menu button can bring it back.
    public ConsumptionTrackerFrame(JFrame menu) {
        this.menu = menu;
        setTitle("WattWise - Energy Consumption Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(buildInputPanel(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);

        refreshTable();
    }

    private JPanel buildInputPanel() {
        JPanel formPanel = new JPanel(new GridLayout(2, 4, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("New monthly bill"));
        formPanel.add(new JLabel("Bill type:"));
        formPanel.add(typeBox);
        formPanel.add(new JLabel("Month (yyyy-mm):"));
        formPanel.add(monthField);
        formPanel.add(new JLabel("Usage (kWh or m3):"));
        formPanel.add(usageField);
        formPanel.add(new JLabel("Price per kWh:"));
        formPanel.add(priceField);

        JButton addButton = new JButton("Add bill");
        JButton deleteButton = new JButton("Delete selected");
        JButton clearButton = new JButton("Clear fields");

        addButton.addActionListener(e -> addBill());
        deleteButton.addActionListener(e -> deleteBill());
        clearButton.addActionListener(e -> clearFields());

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionPanel.add(addButton);
        actionPanel.add(deleteButton);
        actionPanel.add(clearButton);

        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.add(formPanel, BorderLayout.CENTER);
        inputPanel.add(actionPanel, BorderLayout.SOUTH);
        return inputPanel;
    }

    private JPanel buildTablePanel() {
        JButton searchButton = new JButton("Search month");
        JButton showAllButton = new JButton("Show all");

        searchButton.addActionListener(e -> searchByMonth());
        showAllButton.addActionListener(e -> showAllBills());

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search month (yyyy-mm):"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        return tablePanel;
    }

    private JPanel buildBottomPanel() {
        JButton returnButton = new JButton("Return to menu");
        returnButton.addActionListener(e -> returnToMenu());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(returnButton);
        return bottomPanel;
    }

    private void returnToMenu() {
        if (menu != null) {
            menu.setVisible(true);
        }
        dispose();
    }

    private void addBill() {
        BillInput input = readInput();
        if (input == null) {
            return;
        }

        manager.add(input.typeName, input.month, input.usage, input.unitPrice);
        saveData();
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this, "Bill added successfully.");
    }

    private void deleteBill() {
        int row = table.getSelectedRow();
        if (row < 0) {
            showError("Please select a bill from the table first.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete this bill?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int id = (Integer) tableModel.getValueAt(row, 0);
        manager.delete(id);
        saveData();
        refreshTable();
        clearFields();
    }

    private void searchByMonth() {
        String text = searchField.getText().trim();
        if (text.isEmpty()) {
            showAllBills();
            return;
        }

        try {
            shownMonth = YearMonth.parse(text);
            refreshTable();
        } catch (DateTimeParseException ex) {
            showError("Search month must look like 2026-10.");
        }
    }

    private void showAllBills() {
        shownMonth = null;
        searchField.setText("");
        refreshTable();
    }

    private BillInput readInput() {
        YearMonth month;
        try {
            month = YearMonth.parse(monthField.getText().trim());
        } catch (DateTimeParseException ex) {
            showError("Month must look like 2026-10.");
            return null;
        }

        double usage;
        double unitPrice;
        try {
            usage = Double.parseDouble(usageField.getText().trim());
            unitPrice = Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException ex) {
            showError("Usage and price must be numbers, for example 300 and 0.30.");
            return null;
        }

        if (!isValidAmount(usage) || !isValidAmount(unitPrice)) {
            showError("Usage and price must be numbers that are 0 or more.");
            return null;
        }

        return new BillInput((String) typeBox.getSelectedItem(), month, usage, unitPrice);
    }

    // NaN and infinity pass a plain "< 0" check, so test for them separately.
    private boolean isValidAmount(double value) {
        return Double.isFinite(value) && value >= 0;
    }

    private void refreshTable() {
        List<EnergyBill> shown;
        if (shownMonth == null) {
            shown = manager.getAll();
        } else {
            shown = manager.searchByMonth(shownMonth);
        }

        tableModel.setRowCount(0);
        double total = 0;
        for (EnergyBill bill : shown) {
            double cost = bill.calculateCost();
            tableModel.addRow(new Object[]{
                bill.getId(),
                bill.getName(),
                bill.getMonth().toString(),
                bill.getUsage(),
                bill.getUnit(),
                bill.getUnitPrice(),
                String.format("%.2f", cost)
            });
            total += cost;
        }

        setTitle(String.format("WattWise - Energy Consumption Tracker | Shown: %d | Total cost: %.2f",
                shown.size(), total));
    }

    private void clearFields() {
        monthField.setText("");
        usageField.setText("");
        priceField.setText("");
        table.clearSelection();
        monthField.requestFocus();
    }

    private void saveData() {
        try {
            manager.saveToFile();
        } catch (IOException ex) {
            showError("Could not save data: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input error", JOptionPane.ERROR_MESSAGE);
    }

    private static class BillInput {
        private final String typeName;
        private final YearMonth month;
        private final double usage;
        private final double unitPrice;

        private BillInput(String typeName, YearMonth month, double usage, double unitPrice) {
            this.typeName = typeName;
            this.month = month;
            this.usage = usage;
            this.unitPrice = unitPrice;
        }
    }
}
