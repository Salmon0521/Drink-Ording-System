package service.productService;

import bean.product.Product;

import java.util.List;

public interface ProductService {
    public List<Product> showProductByType(String type);
    public List<Product> showType();

}
