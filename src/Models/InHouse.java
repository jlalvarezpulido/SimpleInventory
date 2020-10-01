package Models;
/** Ths is the InHouse class it extends the Part class.
 * @author Jose Alvarez Pulido*/
public class InHouse extends Part{
    private int machineId;
/** InHouse Constructor. uses the super method to extend Part. Has its own value machineId */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId)
    {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
/** Gets the machineId. */
    public int getMachineId() {
        return machineId;
    }
    /** Sets the MachineId. */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
