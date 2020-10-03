package Models;

/**
 * Outsourced class extends Part class.
 * @author Jose Alvarez Pulido
 */
public class Outsourced extends Part{
    private String companyName;
    /** Constructor for Outsourced. Uses super method to extend Part.
     * @param  id this is the generated Id for the outsourced part.
     * @param name this is the name for the outsourced part.
     * @param price this is th price for the outsourced part.
     * @param stock this is the inventory level for the outsourced part.
     * @param min this is the minimum inventory level for the outsourced part.
     * @param max this is the maximum inventory level for the outsourced part.
     * @param companyName this is the company name that manufactures the outsourced part. */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
/** Gets company name.
 * @return company name */
    public String getCompanyName() {
        return companyName;
    }
/** Sets company name.
 * @param companyName this is the company name that will be set by this void method.  */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
