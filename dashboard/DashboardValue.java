package frc.hawklib.dashboard;

import java.util.function.Consumer;
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
    private final Consumer<ValueType> SETTER;

    public DashboardValue(NetworkTable table, String title) { this(table, title, null, null); }
    public DashboardValue(NetworkTable table, String title, Supplier<ValueType> getter) { this(table, title, getter, null); }
    public DashboardValue(NetworkTable table, String title, Consumer<ValueType> setter) { this(table, title, null, setter); }
    
    public DashboardValue(NetworkTable table, String title, Supplier<ValueType> getter, Consumer<ValueType> setter) {
        ENTRY = table.getEntry(title); 
        GETTER = getter;
        SETTER = setter;

        DashboardManager.add(this);
    }

    public ValueType get() { return (ValueType) ENTRY.getValue(); }
    public void set(ValueType value) { ENTRY.setValue(value); }

    public void update() {
        if(GETTER != null) set(GETTER.get());
        if(SETTER != null) SETTER.accept(get());
    }

    public void delete() { ENTRY.unpublish(); }
}
