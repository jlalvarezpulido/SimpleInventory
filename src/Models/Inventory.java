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
    /**
     ** linear Search for id in the list
     *      * @param id is the part id
     *      * @return part if object is found
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




    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index,selectedPart);
    }
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.set(index,newProduct);
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
