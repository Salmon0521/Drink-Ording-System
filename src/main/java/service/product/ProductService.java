package service.product;

import bean.product.Product;

import java.util.List;

public interface ProductService {
    public List<Product> showProducts(String type);
}
