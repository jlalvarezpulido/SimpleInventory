package Models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the Inventory class. all of its values are static. It contains observable lists of Products and Parts.
 * @author Jose Alvarez Pulido
 */
public class Inventory{
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This method adds parts to the allParts List.
     * @param newPart is the product that is added.
     */
    public static void addPart(Part newPart)
    {
        if(newPart != null)
            allParts.add(newPart);
    }

    /**
     * This method adds products to the allProducts List.
     * @param newProduct is the product that is added.
     */
   public static void addProduct(Product newProduct)
   {
       if(newProduct != null)
           allProducts.add(newProduct);
    }
    /**
     ** linear Search for part using part ID.
     *@param partId is the part id
     *@return part if object is found
     *      implemented using an enhanced for-each loop
     */
    public static Part lookupPart(int partId)
    {
        ObservableList<Part> searchParts = getAllParts();
        for (Part part : searchParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * linear search for product in the list suing product ID.
     * @param productId used to search.
     * @return the product.
     * implemented using regular for loop.
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
     * Linear search for part in part list.
     * @param partName used to search for part name in part list.
     * @return namedParts list of parts with similar strings.
     * implemented using an enhanced for-each loop.
     */
    public static ObservableList<Part> lookupPart(String partName)
    {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        for(Part part : Inventory.getAllParts())
        {
            if(part.getName().contains(partName))
            {
                namedParts.add(part);
            }
        }
        return namedParts;
    }

    /**
     * Linear Search for product in product list.
     * @param productName used to search.
     * @return list of products with similar strings.
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
     * Updates parts replacing parts in list using index.
     * @param index the index of the observable list.
     * @param selectedPart used to replace the part in that index.
     */
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index,selectedPart);
    }

    /**
     * Updates products replacing products in list using index.
     * @param index the index of the observable list
     * @param newProduct used to replace the product in that index
     */
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.set(index,newProduct);
    }

    /**
     * Deletes parts in the list.
     * @param selectedPart is the part selected to delete.
     * @return true if part exits and is deleted.
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
     * Deletes products in the list.
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
     * Method used to get all parts in the Inventory.
     * @return the list of all parts in the inventory.
     */
    public static ObservableList<Part> getAllParts(){ return allParts; }

    /**
     * Method used to get all products in the Inventory.
     * @return the list of all products in the Inventory.
     */
    public static ObservableList<Product> getAllProducts(){return allProducts;}


}
