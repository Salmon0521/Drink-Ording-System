package service.product;

import bean.product.Product;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<Product> showProducts(String type){
        return productDAO.getProductsByType(type);
    }
}
