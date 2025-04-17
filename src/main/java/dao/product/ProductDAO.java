package dao.product;

import bean.product.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> getProductsByType(String productType);
    public Integer getProductID(Product product);
}
