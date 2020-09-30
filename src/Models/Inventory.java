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
       if(newProduct != null)
           allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId)
    {
        /**
         ** linear Search for id in the list
         *      * @param id is the part id
         *      * @return part if object is found
         */
        for (Part part : Inventory.getAllParts())
        {
            if (part.getId() == partId)
                return part;
        }
        return null;
    }

    /**
     * linear search
     * @param productId used to search
     * @return the product
     */
    public static Product lookupProduct(int productId)
    {
        for (Product product : Inventory.getAllProducts())
        {
            if (product.getId() == productId)
                return product;
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName){return null;}
    public static ObservableList<Product> lookupProduct(String productName){return null;}




    public static void updatePart(int index, Part selectedPart)
    {
        allParts.remove(index);
        allParts.add(index,selectedPart);
    }
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.remove(index);
        allProducts.add(index,newProduct);

    }

    public static boolean deletePart(Part selectedPart)
    {
        for(Part part : Inventory.getAllParts())
        {
            if(part.getId() == selectedPart.getId())
            {
                Inventory.getAllParts().remove(selectedPart);
                return true;
            }
        }
        return false;
    }
    public static boolean deleteProduct(Product selectedProduct)
    {
      for(Product product : Inventory.getAllProducts())
      {
          if(product.getId() == selectedProduct.getId())
          {
              Inventory.getAllProducts().remove(selectedProduct);
              return true;
          }
      }
      return false;
    }

    public static ObservableList<Part> getAllParts(){ return allParts; }
    public static ObservableList<Product> getAllProducts(){return allProducts;}


}
