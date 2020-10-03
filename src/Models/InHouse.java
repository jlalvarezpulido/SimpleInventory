package Models;
/** Ths is the InHouse class it extends the Part class.
 * @author Jose Alvarez Pulido*/
public class InHouse extends Part{
    private int machineId;
/** InHouse Constructor. uses the super method to extend Part. Has its own value machineId
 * @param id this is the generated id for the InHouse part.
 * @param name this is the name of the inHouse part.
 * @param price this is the price of the inHouse part.
 * @param  stock this is the price of the inHouse part
 * @param min this is the minimum inventory level for the ihHouse part.
 * @param max this is the maximum inventory level for the inHouse part.
 * @param machineId this is the machine id for the inHouse produced part.*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId)
    {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
/** Gets the machineId.
 * @return machineId*/
    public int getMachineId() {
        return machineId;
    }
    /** Sets the MachineId.
     * @param machineId this void method sets the machine id for inHouse part.*/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
