package common;

public abstract class EnergyRecord {
    private int id;
    private String name;

    public EnergyRecord(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getSummary();
}
