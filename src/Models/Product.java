
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
    /** Constructor for Product.
     * @param id this is the generated ID for the product.
     * @param name this is the name for the product.
     * @param price this is the price set for the product.
     * @param stock this is the inventory level for the product.
     * @param min this is the minimum value for the inventory level for the product.
     * @param max thisis the maximum inventory level for the product.*/
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
/** Sets id.
 * @param id The ID used to set the new ID. */
    public void setId(int id) {
        this.id = id;
    }
/** Gets name
 * @return name*/
    public String getName() {
        return name;
    }
/** Sets name.
 * @param name The name that will be set.*/
    public void setName(String name) {
        this.name = name;
    }
/** Gets price
 * @return price. */
    public double getPrice() {
        return price;
    }
/** Sets price.
 * @param price The price that will be set. */
    public void setPrice(double price) {
        this.price = price;
    }
/** Gets Stock.
 * @return stock */
    public int getStock() {
        return stock;
    }
/** Sets Stock.
 * @param stock The inventory level that will be set.*/
    public void setStock(int stock) {
        this.stock = stock;
    }
/** Gets min.
 * @return min*/
    public int getMin() {
        return min;
    }
/** Sets min.
 * @param min The minimum inventory level set. */
    public void setMin(int min) {
        this.min = min;
    }
/** Gets max
 * @return max*/
    public int getMax() {
        return max;
    }
/** Sets max.
 * @param max the maximum inventory level set. */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Associated parts of the Class Part.
     * @param part The part that will be added to the associated parts observable list.
     */
    public void addAssociatedParts(Part part) {
        associatedParts.add(part);
    }
/** Deletes associated parts from list.
 * @param selectedAssociatedPart the part that is selected to be deleted.
 * @return boolean value whether there was a match and successful deletion or not. */
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
 * @return associatedParts list. */
    public ObservableList<Part> getAllAssociatedParts(){ return associatedParts; }
}
