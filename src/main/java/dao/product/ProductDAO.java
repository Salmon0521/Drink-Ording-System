package dao.product;

import bean.product.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> getProductsByType(String productType);
    public List<Product> getMilk();
    public List<Product> getBoutique();
    public List<Product> getLatte();
    public List<Product> getTea();
    public List<Product> getChocolate();
    public List<Product> getCoffee();
    public Product getAddInfo(int ProductID);
    public Integer getProductID(Product product);
    public List<Product> getType();
}
