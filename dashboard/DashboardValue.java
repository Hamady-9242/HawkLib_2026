package frc.hawklib.dashboard;

import java.util.function.Supplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * A simple type-locked entry on the Network Table
 */
@SuppressWarnings("unchecked")
public class DashboardValue<ValueType> {
    private final NetworkTableEntry ENTRY;

    private final Supplier<ValueType> GETTER;

    public DashboardValue(NetworkTable table, String title) { this(table, title, null); }
    public DashboardValue(NetworkTable table, String title, Supplier<ValueType> getter) {
        ENTRY = table.getEntry(title); 
        ENTRY.getTopic();
        GETTER = getter;

        DashboardManager.add(this);
    }

    public ValueType get() { return (ValueType) ENTRY.getValue(); }
    public void set(ValueType value) { ENTRY.setValue(value); }

    public void update() {
        if(GETTER != null) set(GETTER.get());
    }

    public void delete() { ENTRY.unpublish(); }
}
