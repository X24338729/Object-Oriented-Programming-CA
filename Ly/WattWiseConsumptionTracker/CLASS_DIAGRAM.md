# WattWise - Consumption Tracker class diagram

Monthly electricity and gas bills (SDG 7: Home Energy Manager).

```mermaid
classDiagram
    class EnergyRecord {
        <<abstract>>
        -int id
        -String name
        +getId() int
        +getName() String
        +setName(String) void
        +getSummary()* String
    }

    class EnergyBill {
        <<abstract>>
        -YearMonth month
        -double usage
        -double unitPrice
        +getMonth() YearMonth
        +getUsage() double
        +getUnitPrice() double
        +getUnit()* String
        +calculateCost()* double
        +getSummary() String
    }

    class ElectricityBill {
        +getUnit() String
        +calculateCost() double
    }

    class GasBill {
        +getUnit() String
        +calculateCost() double
    }

    class FileSavable {
        <<interface>>
        +saveToFile()* void
        +loadFromFile()* void
    }

    class BillManager {
        -ArrayList~EnergyBill~ bills
        -Path filePath
        -int nextId
        +getAll() List~EnergyBill~
        +add(String, YearMonth, double, double) EnergyBill
        +findById(int) EnergyBill
        +searchByMonth(YearMonth) List~EnergyBill~
        +delete(int) boolean
        +saveToFile() void
        +loadFromFile() void
    }

    class ConsumptionTrackerFrame {
        -BillManager manager
        -JComboBox typeBox
        -JTextField monthField
        -JTextField usageField
        -JTextField priceField
        -JTable table
        +ConsumptionTrackerFrame()
    }

    class WattWiseApp {
        +main(String[]) void
    }

    EnergyRecord <|-- EnergyBill
    EnergyBill <|-- ElectricityBill
    EnergyBill <|-- GasBill
    FileSavable <|.. BillManager
    BillManager "1" o-- "0..*" EnergyBill : manages
    ConsumptionTrackerFrame --> BillManager : uses
    WattWiseApp --> ConsumptionTrackerFrame : launches
```

Cost rules (polymorphism: each child overrides `calculateCost()`):
- Electricity: `kWh * price per kWh + 3.50` (standing charge)
- Gas: `m3 * 11.0 kWh per m3 * price per kWh`
