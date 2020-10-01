package Models;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jose Alvarez Pulido
 */
public class Inventory{
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * add part only if not null
     * @param newPart is the product that is added
     */
    public static void addPart(Part newPart)
    {
        if(newPart != null)
            allParts.add(newPart);
    }

    /**
     * add product only if not null
     * @param newProduct is the product that is added
     */
   public static void addProduct(Product newProduct)
   {
       if(newProduct != null)
           allProducts.add(newProduct);
    }
    /**
     ** linear Search for id in the list
     *      * @param id is the part id
     *      * @return part if object is found
     *      implemented using regular for loop
     */
    public static Part lookupPart(int partId)
    {
        ObservableList<Part> allParts = getAllParts();
        for(int i = 0; i < allParts.size(); i++)
        {
            Part part = allParts.get(i);
            if(part.getId() == partId)
            {
                return part;
            }
        }
        return null;
    }

    /**
     * linear search
     * @param productId used to search
     * @return the product
     * implemented using regular for loop
     */
    public static Product lookupProduct(int productId)
    {
        ObservableList<Product> allProducts = getAllProducts();
        for (int i = 0; i < allProducts.size(); i++)
        {
            Product product = allProducts.get(i);
            if(product.getId() == productId)
            {
                return product;
            }
        }
        return null;
    }

    /**
     *
     * @param partName
     * @returnreturns list of parts with similar strings
     * implemented using an enhanced for-each loop
     */
    public static ObservableList<Part> lookupPart(String partName)
    {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for(Part part : getAllParts())
        {
            if(part.getName().contains(partName))
            {
                namedParts.add(part);
            }
        }
        return namedParts;
    }

    /**
     *
     * @param productName used to search
     * @return list of products with similar strings
     */
    public static ObservableList<Product> lookupProduct(String productName)
    {
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();
        for(Product product : Inventory.getAllProducts())
        {
            if(product.getName().contains(productName))
            {
                namedProduct.add(product);
            }
        }
        return namedProduct;
    }

    /**
     *
     * @param index the index of the observable list
     * @param selectedPart used to replace the part in that index
     */
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index,selectedPart);
    }

    /**
     *
     * @param index the index of the observable list
     * @param newProduct used to replace the product in that index
     */
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.set(index,newProduct);
    }

    /**
     *
     * @param selectedPart is the part selected to delete
     * @return true if part exits and is deleted
     */
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

    /**
     *
     * @param selectedProduct is the product to be deleted.
     * @return returns true if product exists and is deleted.
     */
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

    /**
     *
     * @return the list of all parts in the inventory
     */
    public static ObservableList<Part> getAllParts(){ return allParts; }

    /**
     *
     * @return the list of all products in the inventory
     */
    public static ObservableList<Product> getAllProducts(){return allProducts;}


}
