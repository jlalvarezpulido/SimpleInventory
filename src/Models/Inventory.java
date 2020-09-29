package Models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jose Alvarez Pulido
 */
public class Inventory{
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart)
    {
        if(newPart != null)
            allParts.add(newPart);
    }

   public static void addProduct(Product newProduct)
   {
       allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId){return null;}
    public static Product lookupProduct(int productId){return null;}
    public static ObservableList<Part> lookupPart(String partName){return null;}
    public static ObservableList<Product> lookupProduct(String productName){return null;}
    public static void updatePart(int index, Part selectedPart){}
    public static void updateProduct(int index, Product newProduct){}
    public static boolean deletePart(Part selectedPart){return true;}
    public static boolean deleteProduct(Product selectedProduct){return true;}

    public static ObservableList<Part> getAllParts(){ return allParts; }
    public static ObservableList<Product> getAllProducts(){return allProducts;}


}
