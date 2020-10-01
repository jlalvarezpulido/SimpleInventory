
package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Product class used to define Products.
 * @author Jose Alvarez Pulido
 */
public class Product{

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    /** Constructor for Product. */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
/** Gets Id.
 * @return id */
    public int getId() {
        return id;
    }
/** Sets id. */
    public void setId(int id) {
        this.id = id;
    }
/** Gets name
 * @return name*/
    public String getName() {
        return name;
    }
/** Sets name. */
    public void setName(String name) {
        this.name = name;
    }
/** Gets price
 * @return price. */
    public double getPrice() {
        return price;
    }
/** Sets price. */
    public void setPrice(double price) {
        this.price = price;
    }
/** Gets Stock.
 * @return stock */
    public int getStock() {
        return stock;
    }
/** Sets Stock. */
    public void setStock(int stock) {
        this.stock = stock;
    }
/** Gets min.
 * @return min*/
    public int getMin() {
        return min;
    }
/** Sets min.*/
    public void setMin(int min) {
        this.min = min;
    }
/** Gets max
 * @return max*/
    public int getMax() {
        return max;
    }
/** Sets max. */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Associated parts of the Class Part
     */
    public void addAssociatedParts(Part part) {
        associatedParts.add(part);
    }
/** Deletes associated parts from list. */
    public boolean deleteAssociatedParts(Part selectedAssociatedPart) {
        for(Part part : getAllAssociatedParts())
        {
            if(part.getId() == selectedAssociatedPart.getId())
            {
                getAllAssociatedParts().remove(selectedAssociatedPart);
                return true;
            }
        }
        return false;
    }
/** Returns list of associated parts.
 * @return associatedParts*/
    public ObservableList<Part> getAllAssociatedParts(){ return associatedParts; }
}
